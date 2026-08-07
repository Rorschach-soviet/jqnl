package com.example.stats.model;

import java.time.LocalDate;

public class CriterioFecha {
    private String salida;

    public CriterioFecha(){
        this.salida ="";
    }

    public CriterioFecha entre( LocalDate desde, LocalDate hasta ){
        this.salida = " and fecha between '" + desde + "' and '" + hasta +"'";
        return this;
    }

    public CriterioFecha desde( LocalDate desde){
        this.salida = " and fecha > '" + desde +"'";
        return this;
    }

    public CriterioFecha hasta( LocalDate hasta){
        this.salida = " and fecha < '" + hasta +"'";
        return this;
    }

    public CriterioFecha fecha( LocalDate fecha){
        this.salida = " and fecha = '" + fecha +"'";
        return this;
    }

    public CriterioFecha ultimos(int cantidadDias){
        return this.entre(LocalDate.now().plusDays(cantidadDias * -1), LocalDate.now());
    }

    public CriterioFecha rango(LocalDate fecha, int cantidadDias){
        LocalDate f1, f2;

        if(fecha.isBefore(fecha.plusDays(cantidadDias))){
            f1 = fecha;
            f2 = fecha.plusDays(cantidadDias);
        }else{
            f1 = fecha.plusDays(cantidadDias);
            f2 = fecha;
        }
        return this.entre(f1, f2);
    }

    @Override
    public String toString() {
        return salida;
    }
}
