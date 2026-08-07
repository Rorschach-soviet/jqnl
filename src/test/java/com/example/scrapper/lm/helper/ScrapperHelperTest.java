package com.example.scrapper.lm.helper;

import java.time.LocalDate;

import static org.junit.Assert.*;
import org.junit.Test;

import com.example.scrapper.lm.enums.QUINIELA_LM;
import com.example.scrapper.lm.enums.SORTEO_LM;


public class ScrapperHelperTest {

    private final ScrapperLmHelper sh = ScrapperLmHelper.$();

    @Test
    public void convertDateTest(){
        LocalDate date = sh.convertDate("Resultados del día Viernes 31 de julio de 2026");
        assertEquals(LocalDate.of(2026, 7, 31),date);
    }

    @Test
    public void getIdWebTest(){
        String result = sh.getIdWeb(QUINIELA_LM.CIUDAD, SORTEO_LM.NOCTURNA, 1);
        assertEquals("idQ26_3_N01", result);

        result = sh.getIdWeb(QUINIELA_LM.SANTA_FE, SORTEO_LM.VESPERTINA, 4);
        assertEquals("idQ15_2_N04", result);

        result = sh.getIdWeb(QUINIELA_LM.BUENOS_AIRES, SORTEO_LM.MATUTINA, 12);
        assertEquals("idQ27_1_N12", result);

        result = sh.getIdWeb(QUINIELA_LM.CORDOBA, SORTEO_LM.PRIMERA, 20);
        assertEquals("idQ6_0_N20", result);

        result = sh.getIdWeb(QUINIELA_LM.ENTRE_RIOS, SORTEO_LM.PREVIA, 2);
        assertEquals("idQ14_5_N02", result);
    }

    @Test
    public void createJugadasSqlTest(){
        String result = sh.createJugadasSql(LocalDate.of(2026, 8, 1), 1234, 1, QUINIELA_LM.CIUDAD, SORTEO_LM.NOCTURNA);
        assertEquals("INSERT INTO JUGADAS(FECHA, NUMERO, POS, QUINIELA, SORTEO)VALUES('2026-08-01',1234,1,2,5)", result);
    }

    @Test
    public void createReprocesoSqlTest(){
        String result = sh.createReprocesoSql(LocalDate.of(2026, 8, 1), QUINIELA_LM.CIUDAD, SORTEO_LM.NOCTURNA);
        assertEquals("INSERT INTO REPROCESO(FECHA, QUINIELA, SORTEO)VALUES('2026-08-01',2,5)", result);
    }

}
