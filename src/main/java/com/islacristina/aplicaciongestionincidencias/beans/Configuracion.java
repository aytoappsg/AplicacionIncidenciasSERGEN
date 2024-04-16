package com.islacristina.aplicaciongestionincidencias.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Configuracion {

    @Bean("lblTitulo")
    private String getTitulo(){
        return "Bienvenido a Spring y JavaFX";
    }
}
