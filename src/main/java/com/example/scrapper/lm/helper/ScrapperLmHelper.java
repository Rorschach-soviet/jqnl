package com.example.scrapper.lm.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.example.scrapper.lm.enums.QUINIELA_LM;
import com.example.scrapper.lm.enums.SORTEO_LM;
import com.example.scrapper.lm.mapper.LmEnumMapper;

public class ScrapperLmHelper {
    private static ScrapperLmHelper INSTANCE = null;
    private ScrapperLmHelper(){}
    public static ScrapperLmHelper $(){
        if(INSTANCE ==null){
            INSTANCE = new ScrapperLmHelper();
        }
        return INSTANCE;
    }
    public static void destroy(){
        INSTANCE = null;
        LmEnumMapper.destroy();
    }

    private final String ID_WEB_TEMPLATE = "idQ%d_%d_N%02d";
    private final String JUGADAS_SQL_TEMPLATE="INSERT INTO JUGADAS(FECHA, NUMERO, POS, QUINIELA, SORTEO)VALUES('%s',%d,%d,%d,%d)";
     private final String REPROCESO_SQL_TEMPLATE="INSERT INTO REPROCESO(FECHA, QUINIELA, SORTEO)VALUES('%s',%d,%d)";
    private final LmEnumMapper em = LmEnumMapper.$();

    public LocalDate convertDate(String text){
        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("'resultados del día 'EEEE d 'de' MMMM 'de' yyyy",Locale.of("es"));
        return LocalDate.parse(text.toLowerCase(), formatoEntrada);
    }

    public String getIdWeb(QUINIELA_LM quiniela,  SORTEO_LM sorteo, int numero){
        return String.format(ID_WEB_TEMPLATE, quiniela.code(),sorteo.code(),numero);
    }

    public String createJugadasSql(LocalDate fecha, int numero, int pos, QUINIELA_LM quiniela, SORTEO_LM sorteo){
        return String.format(JUGADAS_SQL_TEMPLATE, fecha.toString(),numero,pos, this.em.quinielFrom(quiniela).code(),this.em.sorteoFrom(sorteo).code());
    }

    public String createReprocesoSql(LocalDate fecha, QUINIELA_LM quiniela, SORTEO_LM sorteo){
        return String.format(REPROCESO_SQL_TEMPLATE, fecha.toString(), this.em.quinielFrom(quiniela).code(),this.em.sorteoFrom(sorteo).code());
    }

    public int normalizeNumber(String number){
        try{
            if(number !=null){
                if(number.length() > 3){
                    return Integer.parseInt(number.substring(number.length()-4));
                }
            }
        }catch(NumberFormatException e){
            return 0;
        }
    return 0;
    }
}
