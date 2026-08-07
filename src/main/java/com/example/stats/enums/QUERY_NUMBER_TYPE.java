package com.example.stats.enums;

public enum QUERY_NUMBER_TYPE {
    NUMBER1C("number1c"),
    NUMBER2C("number2c"),
    NUMBER3C("number3c"),
    NUMBER4C("numero_id"),
    DECENA("decena"),
    CENTENA("centena"),
    UNIDAD_MIL("unidadMil");


    private final String text;

    private QUERY_NUMBER_TYPE(String text){
        this.text = text;
    }

    public String text(){
        return this.text;
    }
}