package com.islacristina.aplicaciongestionincidencias.services;

import com.islacristina.aplicaciongestionincidencias.model.EstadoIncidencia;
import com.islacristina.aplicaciongestionincidencias.repositories.EstadoIncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoIncidenciaService {

    private final EstadoIncidenciaRepository estadoIncidenciaRepository;

    @Autowired
    public EstadoIncidenciaService(EstadoIncidenciaRepository estadoIncidenciaRepository) {
        this.estadoIncidenciaRepository = estadoIncidenciaRepository;
    }

    public EstadoIncidencia findByNombre(String nombre) {
        return estadoIncidenciaRepository.findByNombre(nombre);
    }
}