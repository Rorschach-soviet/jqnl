package com.example.scrapper.lm.enums;

import java.util.Arrays;

public enum QUINIELA_LM {
    BUENOS_AIRES(27),
    CIUDAD(26),
    SANTA_FE(15),
    CORDOBA(6),
    ENTRE_RIOS(14);

    private final int code;

    private QUINIELA_LM(int code){
        this.code = code;
    }

    public int code(){
        return this.code;
    }

    public static QUINIELA_LM get(int code){
        return Arrays.stream(QUINIELA_LM.values())
        .filter(q -> q.code == code)
        .findFirst()
        .orElse(null);
    }

}
