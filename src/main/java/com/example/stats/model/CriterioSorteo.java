package com.example.stats.model;

import java.util.Arrays;

import com.example.scrapper.enums.QUINIELA;
import com.example.scrapper.enums.SORTEO;

public class CriterioSorteo {
    private QUINIELA quiniela;
    private SORTEO sorteo;
    private Integer pos;
    private CriterioFecha fecha;
    

    @Override
    public String toString(){
        String response = String.join("", Arrays.asList(quinielaQuery(),sorteoQuery(),posQuery(),fechaQuery().toString()));
        if(!response.isEmpty()){
            return "where 1=1" + response;
        }
        return "";
    }

    private String quinielaQuery(){
        if(this.quiniela != null){
            return " and quiniela=" + this.quiniela.code();
        }
        return "";
    }

    private String sorteoQuery(){
        if(this.sorteo != null){
            return " and sorteo=" + this.sorteo.code();
        }
        return "";
    }

    private String posQuery(){
        if(this.pos != null){
            return " and pos=" + this.pos;
        }
        return "";
    }

    private CriterioFecha fechaQuery(){
        if(this.fecha != null){
            return this.fecha;
        }
        return new CriterioFecha();
    }

    public CriterioSorteo quiniela(QUINIELA quiniela) {
        this.quiniela = quiniela;
        return this;
    }

    public CriterioSorteo sorteo(SORTEO sorteo) {
        this.sorteo = sorteo;
        return this;
    }

    public CriterioSorteo pos(Integer pos) {
        this.pos = pos;
        return this;
    }

    public CriterioSorteo fecha(CriterioFecha fecha) {
        this.fecha = fecha;
        return this;
    }


}
