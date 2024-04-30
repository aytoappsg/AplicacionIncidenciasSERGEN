package com.islacristina.aplicaciongestionincidencias.services;

import com.islacristina.aplicaciongestionincidencias.model.*;
import com.islacristina.aplicaciongestionincidencias.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncidenciaService {

    @Autowired
    private final ProcedenciaRepository procedenciaRepository;

    @Autowired
    private final TipoLugarRepository tipoLugarRepository;

    @Autowired
    private final LugarRepository lugarRepository;

    @Autowired
    private final IncidenciaRepository incidenciaRepository;

    @Autowired
    private final TerceroRepository terceroRepository;

    @Autowired
    private final UbicacionRepository ubicacionRepository;

    @Autowired
    private final UbicacionIncidenciaRepository ubicacionIncidenciaRepository;


    public IncidenciaService(ProcedenciaRepository procedenciaRepository, TipoLugarRepository tipoLugarRepository, LugarRepository lugarRepository, IncidenciaRepository incidenciaRepository, TerceroRepository terceroRepository, UbicacionRepository ubicacionRepository, UbicacionIncidenciaRepository ubicacionIncidenciaRepository){
        this.procedenciaRepository = procedenciaRepository;
        this.tipoLugarRepository = tipoLugarRepository;
        this.lugarRepository = lugarRepository;
        this.incidenciaRepository = incidenciaRepository;
        this.terceroRepository = terceroRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.ubicacionIncidenciaRepository = ubicacionIncidenciaRepository;
    }

    public List<Procedencia> getAllProcedencia(){
        return procedenciaRepository.findAll();
    }

    public List<TipoLugar> getAllTipoUbicacion(){
        return tipoLugarRepository.findAll();
    }

    public List<Lugar> getLugaresTipoProcedencia(int idTipoLugar){
        List<Lugar> lugares = new ArrayList<>();
        List<Ubicacion> ubicaciones = ubicacionRepository.findByTipoLugar(idTipoLugar);
        for (Ubicacion u : ubicaciones){
            lugares.add(this.lugarRepository.findById(u.getLugar().getIdLugar()).get());
        }
        return lugares;
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
