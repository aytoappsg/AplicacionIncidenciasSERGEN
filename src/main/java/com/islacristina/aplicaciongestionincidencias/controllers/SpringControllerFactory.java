package com.islacristina.aplicaciongestionincidencias.controllers;

import javafx.util.Callback;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringControllerFactory implements Callback<Class<?>, Object> {

    private final ApplicationContext context;

    public SpringControllerFactory(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object call(Class<?> aClass) {
        return context.getBean(aClass);
    }
}