package com.example.scrapper.enums;

import java.util.Arrays;

public enum SORTEO {
    PREVIA(1),
    PRIMERA(2),
    MATUTINA(3),
    VESPERTINA(4),
    NOCTURNA(5);

private final int code;

    private SORTEO(int code){
        this.code = code;
    }

    public int code(){
        return code;
    }

    public static SORTEO get(int code){
        return Arrays.stream(SORTEO.values())
        .filter(q -> q.code == code)
        .findFirst()
        .orElse(null);
    }
}
