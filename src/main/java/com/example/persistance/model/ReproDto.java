package com.example.persistance.model;

import java.time.LocalDate;

import com.example.scrapper.enums.QUINIELA;
import com.example.scrapper.enums.SORTEO;

public class ReproDto {
    private int id;
    private LocalDate fecha;
    private QUINIELA quiniela;
    private SORTEO sorteo;

    public ReproDto(){}
    
    public ReproDto(int id,LocalDate fecha, QUINIELA quiniela, SORTEO sorteo) {
        this.id = id;
        this.fecha = fecha;
        this.quiniela = quiniela;
        this.sorteo = sorteo;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public QUINIELA getQuiniela() {
        return quiniela;
    }

    public void setQuiniela(QUINIELA quiniela) {
        this.quiniela = quiniela;
    }

    public SORTEO getSorteo() {
        return sorteo;
    }

    public void setSorteo(SORTEO sorteo) {
        this.sorteo = sorteo;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String toString(){
        return "ReproDto('"+ this.fecha +"', "+ this.quiniela + ", " + this.sorteo +")";
    }
}
