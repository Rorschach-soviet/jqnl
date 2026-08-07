package com.example.stats.model;

import com.example.service.display.annotations.Columna;

public class ResultDto {

    @Columna(nombre = "Numero")
    private int numero;

    @Columna(nombre = "Cantidad")
    private int cantidad;

    public ResultDto(int cantidad, int numero) {
        this.cantidad = cantidad;
        this.numero = numero;
    }

    public ResultDto() {}


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ResultDto{");
        sb.append("numero=").append(numero);
        sb.append(", cantidad=").append(cantidad);
        sb.append('}');
        return sb.toString();
    }



}
