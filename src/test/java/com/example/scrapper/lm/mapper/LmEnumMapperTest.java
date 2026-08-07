package com.example.scrapper.lm.mapper;

import com.example.scrapper.enums.SORTEO;
import com.example.scrapper.lm.enums.SORTEO_LM;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.scrapper.enums.QUINIELA;
import com.example.scrapper.lm.enums.QUINIELA_LM;

public class LmEnumMapperTest {
    LmEnumMapper em = LmEnumMapper.$();

    @Test
    public void quinielaMapperFromTest(){
       QUINIELA result = em.quinielFrom(QUINIELA_LM.CIUDAD);
       assertEquals(QUINIELA.CIUDAD,result);

       result = em.quinielFrom(QUINIELA_LM.BUENOS_AIRES);
       assertEquals(QUINIELA.BUENOS_AIRES,result);

       
       result = em.quinielFrom(QUINIELA_LM.SANTA_FE);
       assertEquals(QUINIELA.SANTA_FE,result);
       
       result = em.quinielFrom(QUINIELA_LM.CORDOBA);
       assertEquals(QUINIELA.CORDOBA,result);
       
       result = em.quinielFrom(QUINIELA_LM.ENTRE_RIOS);
       assertEquals(QUINIELA.ENTRE_RIOS,result);
    }

        @Test
    public void quinielaMapperToTest(){
       QUINIELA_LM result = em.quinielTo(QUINIELA.CIUDAD);
       assertEquals(QUINIELA_LM.CIUDAD,result);

       result = em.quinielTo(QUINIELA.BUENOS_AIRES);
       assertEquals(QUINIELA_LM.BUENOS_AIRES,result);
       
       result = em.quinielTo(QUINIELA.SANTA_FE);
       assertEquals(QUINIELA_LM.SANTA_FE,result);
       
       result = em.quinielTo(QUINIELA.CORDOBA);
       assertEquals(QUINIELA_LM.CORDOBA,result);
       
       result = em.quinielTo(QUINIELA.ENTRE_RIOS);
       assertEquals(QUINIELA_LM.ENTRE_RIOS,result);
    }

    @Test
    public void sorteoMapperFromTest(){
       SORTEO result = em.sorteoFrom(SORTEO_LM.PREVIA);
       assertEquals(SORTEO.PREVIA,result);

       result = em.sorteoFrom(SORTEO_LM.PRIMERA);
       assertEquals(SORTEO.PRIMERA,result);

       result = em.sorteoFrom(SORTEO_LM.MATUTINA);
       assertEquals(SORTEO.MATUTINA,result);

       result = em.sorteoFrom(SORTEO_LM.VESPERTINA);
       assertEquals(SORTEO.VESPERTINA,result);

       result = em.sorteoFrom(SORTEO_LM.NOCTURNA);
       assertEquals(SORTEO.NOCTURNA,result);
    }

        @Test
    public void sorteoMapperToTest(){
        SORTEO_LM result = em.sorteoTo(SORTEO.PREVIA);
        assertEquals(SORTEO_LM.PREVIA,result);
        
        result = em.sorteoTo(SORTEO.PRIMERA);
        assertEquals(SORTEO_LM.PRIMERA,result);

        result = em.sorteoTo(SORTEO.MATUTINA);
        assertEquals(SORTEO_LM.MATUTINA,result);

        result = em.sorteoTo(SORTEO.VESPERTINA);
        assertEquals(SORTEO_LM.VESPERTINA,result);

        result = em.sorteoTo(SORTEO.NOCTURNA);
        assertEquals(SORTEO_LM.NOCTURNA,result);
    }

}
