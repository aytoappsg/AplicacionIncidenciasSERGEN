package com.islacristina.aplicaciongestionincidencias.services;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import com.islacristina.aplicaciongestionincidencias.model.UbicacionIncidencia;
import com.islacristina.aplicaciongestionincidencias.repositories.UbicacionIncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumenIncidenciaService {
    @Autowired
    private final UbicacionIncidenciaRepository ubicacionIncidenciaRepository;

    public ResumenIncidenciaService(UbicacionIncidenciaRepository ubicacionIncidenciaRepository) {
        this.ubicacionIncidenciaRepository = ubicacionIncidenciaRepository;
    }

    public List<UbicacionIncidencia> getAllUbicacionIncidenciaByInciencia(Integer idIncidencia) {
        return ubicacionIncidenciaRepository.findByIncidencia_NumOrden(idIncidencia);
    }
}
