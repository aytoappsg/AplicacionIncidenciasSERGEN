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

    public Procedencia getProcedenciaByTipoProcedencia(String tipoProcedencia) {
        return procedenciaRepository.findByTipoProcedencia(tipoProcedencia);
    }

    public List<TipoLugar> getAllTipoUbicacion(){
        return tipoLugarRepository.findAll();
    }

    public List<String> getNombreLugaresTipoLugar(String tipoLugar, String nombreLugar){
        Integer idTipoLugar = tipoLugarRepository.findByTipoLugar(tipoLugar).getIdTipoLugar();
        List<String> nombresLugares = new ArrayList<>();
        List<Ubicacion> ubicaciones = ubicacionRepository.findByTipoLugarAndNombre(idTipoLugar, nombreLugar);
        for (Ubicacion u : ubicaciones){
            nombresLugares.add(this.lugarRepository.findById(u.getLugar().getIdLugar()).get().getNombreLugar());
        }
        return nombresLugares;
    }

    public Tercero getTerceroByDni(String dni){
        return terceroRepository.findByDniCif(dni);
    }

    public Tercero getTerceroByEmail(String email){
        return terceroRepository.findByEmail(email);
    }

    public void updateTerceroByDni(String dni, Tercero updatedTercero){
        terceroRepository.saveAndFlush(updatedTercero);
    }

    public void updateTerceroByEmail(String email, Tercero updatedTercero){
        terceroRepository.saveAndFlush(updatedTercero);
    }

    public void saveTercero(Tercero tercero) {
        terceroRepository.saveAndFlush(tercero);
    }

    public void saveIncidencia(Incidencia incidencia) {
        incidenciaRepository.saveAndFlush(incidencia);
    }

    public void saveUbicacionIncidencia(UbicacionIncidencia ubicacionIncidencia) {
        ubicacionIncidenciaRepository.saveAndFlush(ubicacionIncidencia);
    }

    public TipoLugar getTipoLugarByTipoLugar(String value) {
        return tipoLugarRepository.findByTipoLugar(value);
    }

    public Ubicacion getUbicacionByTipoLugarAndNombreLugar(String tipoLugar, String nombreLugar){
        Integer idTipoLugar = tipoLugarRepository.findByTipoLugar(tipoLugar).getIdTipoLugar();
        Integer idLugar = lugarRepository.findByNombreLugar(nombreLugar).getIdLugar();
        return ubicacionRepository.findByTipoLugar_IdTipoLugarAndLugar_IdLugar(idTipoLugar, idLugar);
    }

    public List<Incidencia> getAllIncidencias() {
        return incidenciaRepository.findAll();
    }
}
