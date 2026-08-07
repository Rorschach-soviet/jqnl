package com.example.scrapper.lm.mapper;

import com.example.scrapper.enums.QUINIELA;
import com.example.scrapper.enums.SORTEO;
import com.example.scrapper.lm.enums.SORTEO_LM;
import com.example.scrapper.lm.enums.QUINIELA_LM;

public class LmEnumMapper {
    private static LmEnumMapper INSTANCE = null;
    private LmEnumMapper(){}
    public static LmEnumMapper $(){
        if(INSTANCE ==null){
            INSTANCE = new LmEnumMapper();
        }
        return INSTANCE;
    }
    public static void destroy(){
        INSTANCE = null;
    }

    public SORTEO sorteoFrom(SORTEO_LM sorteo){
        switch(sorteo){
            case PREVIA -> {return SORTEO.PREVIA;}
            case PRIMERA -> {return SORTEO.PRIMERA;}
            case MATUTINA -> {return SORTEO.MATUTINA;}
            case VESPERTINA ->{return SORTEO.VESPERTINA;}
            case NOCTURNA -> {return SORTEO.NOCTURNA;}
            default -> throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public SORTEO_LM sorteoTo(SORTEO sorteo){
        switch(sorteo){
            case PREVIA -> {return SORTEO_LM.PREVIA;}
            case PRIMERA -> {return SORTEO_LM.PRIMERA;}
            case MATUTINA -> {return SORTEO_LM.MATUTINA;}
            case VESPERTINA ->{return SORTEO_LM.VESPERTINA;}
            case NOCTURNA -> {return SORTEO_LM.NOCTURNA;}
            default -> throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public QUINIELA quinielFrom(QUINIELA_LM sorteo){
        switch(sorteo){
            case BUENOS_AIRES -> {return QUINIELA.BUENOS_AIRES;}
            case CIUDAD -> {return QUINIELA.CIUDAD;}
            case SANTA_FE -> {return QUINIELA.SANTA_FE;}
            case CORDOBA ->{return QUINIELA.CORDOBA;}
            case ENTRE_RIOS -> {return QUINIELA.ENTRE_RIOS;}
            default -> throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public QUINIELA_LM quinielTo(QUINIELA sorteo){
        switch(sorteo){
            case BUENOS_AIRES -> {return QUINIELA_LM.BUENOS_AIRES;}
            case CIUDAD -> {return QUINIELA_LM.CIUDAD;}
            case SANTA_FE -> {return QUINIELA_LM.SANTA_FE;}
            case CORDOBA ->{return QUINIELA_LM.CORDOBA;}
            case ENTRE_RIOS -> {return QUINIELA_LM.ENTRE_RIOS;}
            default -> throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
