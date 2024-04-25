package com.islacristina.aplicaciongestionincidencias.services;

import com.islacristina.aplicaciongestionincidencias.model.*;
import com.islacristina.aplicaciongestionincidencias.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciaService {

    @Autowired
    private final ProcedenciaRepository procedenciaRepository;

    @Autowired
    private final TipoUbicacionRepository tipoUbicacionRepository;

    @Autowired
    private final LugarRepository lugarRepository;

    @Autowired
    private final IncidenciaRepository incidenciaRepository;

    @Autowired
    private final TerceroRepository terceroRepository;


    public IncidenciaService(ProcedenciaRepository procedenciaRepository, TipoUbicacionRepository tipoUbicacionRepository, LugarRepository lugarRepository, IncidenciaRepository incidenciaRepository, TerceroRepository terceroRepository){
        this.procedenciaRepository = procedenciaRepository;
        this.tipoUbicacionRepository = tipoUbicacionRepository;
        this.lugarRepository = lugarRepository;
        this.incidenciaRepository = incidenciaRepository;
        this.terceroRepository = terceroRepository;
    }

    public List<Procedencia> getAllProcedencia(){
        return procedenciaRepository.findAll();
    }

    public List<TipoUbicacion> getAllTipoUbicacion(){
        return tipoUbicacionRepository.findAll();
    }

    public List<Lugar> getAllLugar(){
        return lugarRepository.findAll();
    }

    public Procedencia saveProcedencia(Procedencia procedencia) {
        return procedenciaRepository.save(procedencia);
    }

    public Tercero saveTercero(Tercero tercero) {
        return terceroRepository.save(tercero);
    }

    public Incidencia saveIncidencia(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }



    public void insertIncidencia(Incidencia incidencia) {
        incidenciaRepository.save(incidencia);
    }

}
