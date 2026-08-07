package com.example.scrapper.ns;

import com.example.scrapper.interfaz.ScrapperService;

public class ScrapperServiceNSImpl implements ScrapperService {
    private static ScrapperService INSTANCE = null;
    private ScrapperServiceNSImpl(){}
    public static ScrapperService $(){
        if(INSTANCE ==null){
            INSTANCE = new ScrapperServiceNSImpl();
        }
        return INSTANCE;
    }
    public static void destroy(){
        INSTANCE = null;
    }

    @Override
    public void actualizarDb() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
