package com.example.service.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CommonHelper {
    private static CommonHelper INSTANCE = null;
    private CommonHelper(){}
    public static CommonHelper $(){
        if(INSTANCE ==null){
            INSTANCE = new CommonHelper();
        }
        return INSTANCE;
    }
    public static void destroy(){
        INSTANCE = null;
    }

    public List<LocalDate> getDates(LocalDate from, LocalDate to){
        return from.datesUntil(to.plusDays(1))
        .filter(date ->{
            DayOfWeek dow = date.getDayOfWeek();
            return dow != DayOfWeek.SUNDAY;
        })
        .collect(Collectors.toList());
    }

}
