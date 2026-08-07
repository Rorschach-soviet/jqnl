package com.example.scrapper.enums;

import java.util.Arrays;

public enum QUINIELA {
    BUENOS_AIRES(1),
    CIUDAD(2),
    SANTA_FE(3),
    CORDOBA(4),
    ENTRE_RIOS(5);

    private final int code;

    private QUINIELA(int code){
        this.code = code;
    }

    public int code(){
        return code;
    }

    public static QUINIELA get(int code){
        return Arrays.stream(QUINIELA.values())
        .filter(q -> q.code == code)
        .findFirst()
        .orElse(null);
    }
}
