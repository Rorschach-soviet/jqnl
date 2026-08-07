package com.example.service.display.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Columna {
    String nombre();
    int order() default Integer.MAX_VALUE;
}
