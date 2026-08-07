package com.example.scrapper.lm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.example.persistance.model.ReproDto;
import com.example.persistance.repository.CommonRepository;
import com.example.scrapper.interfaz.ScrapperService;
import com.example.scrapper.lm.enums.QUINIELA_LM;
import com.example.scrapper.lm.enums.SORTEO_LM;
import com.example.scrapper.lm.helper.ScrapperLmHelper;
import com.example.scrapper.lm.mapper.LmEnumMapper;
import com.example.service.helper.CommonHelper;

public class ScrapperServiceLMImpl implements ScrapperService {
    private static ScrapperService INSTANCE = null;
    private ScrapperServiceLMImpl(){}
    public static ScrapperService $(){
        if(INSTANCE ==null){
            INSTANCE = new ScrapperServiceLMImpl();
        }
        return INSTANCE;
    }
    public static void destroy(){
        INSTANCE = null;
        ScrapperLmHelper.destroy();
        CommonHelper.destroy();
        CommonRepository.destroy();
        LmEnumMapper.destroy();
    }

    private final ScrapperLmHelper sh = ScrapperLmHelper.$();
    private final CommonRepository cr = CommonRepository.$();
    private final CommonHelper ch = CommonHelper.$();
    private final LmEnumMapper em = LmEnumMapper.$();

    private final String URL = "https://www.loteriasmundiales.com.ar/Quinielas/ciudad";
    private final String REQUEST_BODY_TEMPLATE = "Fecha=%s&QuinielaId=26";

    public Document loadPage(LocalDate fecha){
        try {
            Connection.Response response = Jsoup.connect(URL)
            .requestBody(String.format(REQUEST_BODY_TEMPLATE, fecha.toString()))
            .method(Connection.Method.POST)
            .ignoreContentType(true)
            .execute();
            if(response.statusCode() == 200){
                return response.parse();
            }
        } catch (IOException ex) {
            System.getLogger(ScrapperServiceLMImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    public void parseHtml(Document html, LocalDate fechaActual){
        Element h2 = html.selectFirst("h2");
        LocalDate fechaWeb = sh.convertDate(h2.text());
        if(fechaWeb.equals(fechaActual)){
            EnumSet.allOf(QUINIELA_LM.class)
                .forEach(q -> {
                    EnumSet.allOf(SORTEO_LM.class)
                        .forEach(s -> {
                            getResultados(html, fechaWeb, q, s);
                        });
                });
        }
    }

    public void getResultados(Document html, LocalDate fecha, QUINIELA_LM quiniela, SORTEO_LM sorteo){
        //si no existe el primer premio no existe el resto y se va a reproceso 
        Element premio = html.getElementById(sh.getIdWeb(quiniela, sorteo, 1));
        cr.clearBatch();
        if(premio != null){
            //se reutiliza el primer premio
            cr.addBatch(sh.createJugadasSql(fecha, sh.normalizeNumber(premio.text()), 1, quiniela, sorteo));
            for(int i = 2; i <=20;i++){ 
                premio = html.getElementById(sh.getIdWeb(quiniela, sorteo, i));
                cr.addBatch(sh.createJugadasSql(fecha, sh.normalizeNumber(premio.text()), i, quiniela, sorteo));
            }
        }else{
            cr.addBatch(sh.createReprocesoSql(fecha, quiniela, sorteo));
        }
        cr.executeBatch();
    }

    public void reprocesar(){
        Map<LocalDate,List<ReproDto>> repro = cr.getReprocesos();
        
        repro.forEach((k,v)->{
            Document html = loadPage(k);
            v.forEach(r -> {
                cr.borrarReprocesos(r.getId());
                getResultados(html,k,em.quinielTo(r.getQuiniela()),em.sorteoTo(r.getSorteo()));
            });
        });

        cr.limpiarReprocesados();
    }

    @Override
    public void actualizarDb() {
        LocalDate fechaInicio = cr.getLasDate().plusDays(1);
        List<LocalDate> fechas = ch.getDates(fechaInicio, LocalDate.now());

        System.out.println("Actualizando desde el " + fechaInicio.toString());

        reprocesar();

        int count = 0;
        float percent = 0.0f;

        final int total = fechas.size();

        for(LocalDate f : fechas){
            parseHtml(loadPage(f),f);
            count++;
            percent =(count/total)*100;
            System.out.print("\rCompletado: " +  percent + "%" );
            System.out.flush(); 
        }
        if(count>0)System.out.println("\n");
        System.out.println("Listo!");
    }
}
