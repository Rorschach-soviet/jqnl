package com.example.service.display;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.service.display.annotations.Columna;
import com.example.service.display.model.CanvasColumn;
import com.example.service.display.printer.CanvasService;

/**
 * CanvasForm
 */
public class TableReport {
    private static TableReport INSTANCE = null;
    private TableReport(){}
    public static TableReport $(){
        if(INSTANCE ==null){
            INSTANCE = new TableReport();
        }
        return INSTANCE;
    }
    public static void destroy(){
        INSTANCE = null;
    }

    public List<String> create(List<?> list, Class<?> clazz){
        List<String> result = new ArrayList<>();
        List<CanvasColumn> columns = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(Columna.class)){
                Columna c = method.getAnnotation(Columna.class);
                columns.add(new CanvasColumn(method,
                     createGetterMethod(method),
                      c.nombre(), c.order()));
            }
        }


         String titles = columns.stream()
            .map(CanvasColumn::getName )
            .collect(Collectors.joining("\t"));


        result.add(titles);

        

        return null;
    }

    private String createGetterMethod(Method method){
        String input = method.getName();
        String prefix = "get";
        if(method.getReturnType().equals(Boolean.class)){
            prefix =  "is";
        }
        return prefix + input.substring(0, 1).toUpperCase() + input.substring(1);
    }


}
