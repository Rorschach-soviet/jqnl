package com.example.scrapper.lm.enums;

import java.util.Arrays;

public enum SORTEO_LM {
    PREVIA(5),
    PRIMERA(0),
    MATUTINA(1),
    VESPERTINA(2),
    NOCTURNA(3);

    private final int code;

    private SORTEO_LM(int code){
        this.code = code;
    }

    public int code(){
        return code;
    }

    public static SORTEO_LM get(int code){
        return Arrays.stream(SORTEO_LM.values())
        .filter(q -> q.code == code)
        .findFirst()
        .orElse(null);
    }
}
