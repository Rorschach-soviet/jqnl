package com.example.scrapper;

import com.example.scrapper.enums.SCRAPPER_TYPE;
import com.example.scrapper.interfaz.ScrapperService;
import com.example.scrapper.lm.ScrapperServiceLMImpl;
import com.example.scrapper.ns.ScrapperServiceNSImpl;

public class ScrapperServiceFactory {
    public static ScrapperService getScrapperService(SCRAPPER_TYPE type){
        switch (type){
            case LM -> {
                return ScrapperServiceLMImpl.$();
            }
            case NS -> {
                return ScrapperServiceNSImpl.$();
            }
            default -> throw new UnsupportedOperationException("Not supported yet.");
        }

    }
}
