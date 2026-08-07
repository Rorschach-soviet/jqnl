package com.example;

import java.util.List;

import com.example.persistance.DBService;
import com.example.scrapper.ScrapperServiceFactory;
import com.example.scrapper.enums.QUINIELA;
import com.example.scrapper.enums.SCRAPPER_TYPE;
import com.example.scrapper.interfaz.ScrapperService;
import com.example.service.display.DisplayService;
import com.example.stats.enums.QUERY_NUMBER_TYPE;
import com.example.stats.model.CriterioFecha;
import com.example.stats.model.CriterioSorteo;
import com.example.stats.model.ResultDto;
import com.example.stats.repository.StatsRepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        System.out.println( "Hello World!" );
        DBService.$().connect();
        ScrapperService ss = ScrapperServiceFactory.getScrapperService(SCRAPPER_TYPE.LM);
        //ss.actualizarDb();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        List<ResultDto> list = StatsRepository.$()
          .getSalidos(QUERY_NUMBER_TYPE.NUMBER1C, 
            new CriterioSorteo()
            .quiniela(QUINIELA.BUENOS_AIRES)
            .sorteo(null)//SORTEO.MATUTINA)
            .pos(1)
            .fecha(new CriterioFecha()
                .ultimos(90)
            )
        );

        DisplayService.$().mainMenu();

        System.out.println("╔══════╗");
        System.out.println("║cuenta║");
        System.out.println("╠══════╣");
        System.out.println("║   1  ║");
        System.out.println("║   2  ║");
        System.out.println("║   3  ║");
        System.out.println("║   4  ║");
        System.out.println("╚══════╝");
        list.forEach(System.out::println);
        DBService.$().close();
    }
}
