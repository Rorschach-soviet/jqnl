package com.example.service.display.model;

import java.lang.reflect.Method;

public class CanvasColumn {
    private Method getter;
    private String name;
    private String getterName;
    private int order;
    
    public CanvasColumn(Method getter, String getterName, String name, int order) {
        this.getter = getter;
        this.getterName = getter.getName();
        this.name = name;
        this.order = order;
    }

    public Method getGetter() {
        return getter;
    }
    
    public String getName() {
        return name;
    }
    public int getOrder() {
        return order;
    }

    public String getGetterName() {
        return getterName;
    }

}
