package com.islacristina.aplicaciongestionincidencias.services;

import com.islacristina.aplicaciongestionincidencias.model.Lugar;
import com.islacristina.aplicaciongestionincidencias.model.Procedencia;
import com.islacristina.aplicaciongestionincidencias.model.TipoUbicacion;
import com.islacristina.aplicaciongestionincidencias.repositories.LugarRepository;
import com.islacristina.aplicaciongestionincidencias.repositories.ProcedenciaRepository;
import com.islacristina.aplicaciongestionincidencias.repositories.TipoUbicacionRepository;
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

    public IncidenciaService(ProcedenciaRepository procedenciaRepository, TipoUbicacionRepository tipoUbicacionRepository, LugarRepository lugarRepository){
        this.procedenciaRepository = procedenciaRepository;
        this.tipoUbicacionRepository = tipoUbicacionRepository;
        this.lugarRepository = lugarRepository;
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
}
