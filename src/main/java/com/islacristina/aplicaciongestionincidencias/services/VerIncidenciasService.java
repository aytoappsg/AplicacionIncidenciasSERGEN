package com.islacristina.aplicaciongestionincidencias.services;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import com.islacristina.aplicaciongestionincidencias.model.Ubicacion;
import com.islacristina.aplicaciongestionincidencias.model.UbicacionIncidencia;
import com.islacristina.aplicaciongestionincidencias.repositories.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class VerIncidenciasService {

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





    public VerIncidenciasService(ProcedenciaRepository procedenciaRepository, TipoLugarRepository tipoLugarRepository, LugarRepository lugarRepository, IncidenciaRepository incidenciaRepository, TerceroRepository terceroRepository, UbicacionRepository ubicacionRepository, UbicacionIncidenciaRepository ubicacionIncidenciaRepository) {
        this.procedenciaRepository = procedenciaRepository;
        this.tipoLugarRepository = tipoLugarRepository;
        this.lugarRepository = lugarRepository;
        this.incidenciaRepository = incidenciaRepository;
        this.terceroRepository = terceroRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.ubicacionIncidenciaRepository = ubicacionIncidenciaRepository;
    }

    //método para que devuelva todas las inciencias
    public ObservableList<Map> getAllIncidencias() {

        ObservableList<Map> incidenciasList = FXCollections.observableArrayList();

        List<Incidencia> listaIncidencias = incidenciaRepository.findAll();
        for (Incidencia incidencia : listaIncidencias) {
            Map<String, Object> coleccion = new HashMap<>();
            coleccion.put("numOrden", (incidencia.getNumOrden() != 0) ? incidencia.getNumOrden() : "");
            coleccion.put("nuestraReferencia", (incidencia.getNuestraReferencia() != null) ? incidencia.getNuestraReferencia() : "");
            coleccion.put("numRegistroAyuntamiento", (incidencia.getNumRegistroAyuntamiento() != null) ? incidencia.getNumRegistroAyuntamiento() : "");

            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Date fechaNotificacion = incidencia.getFechaNotificacion();
            LocalDate fechaNotif = fechaNotificacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String fechaNotificacionFormateada = fechaNotif.format(pattern);
            coleccion.put("fechaNotificacion", (incidencia.getFechaNotificacion() != null) ? fechaNotificacionFormateada : "");

            Date fechaServiciosGenerales = incidencia.getFechaServiciosGenerales();
            LocalDate fechaServGen = fechaServiciosGenerales.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String fechaServGenFormateada = fechaServGen.format(pattern);
            coleccion.put("fechaServiciosGenerales", (incidencia.getFechaServiciosGenerales() != null) ?  fechaServGenFormateada : "");

            List<UbicacionIncidencia> ubicacionIncidencias = ubicacionIncidenciaRepository.findByIncidencia_NumOrden(incidencia.getNumOrden());
            String tipoLugar = (ubicacionIncidencias != null && !ubicacionIncidencias.isEmpty() && ubicacionIncidencias.get(0).getUbicacion().getTipoLugar() != null) ? ubicacionIncidencias.get(0).getUbicacion().getTipoLugar().getTipoLugar() : "";
            String nombreLugar = (ubicacionIncidencias != null && !ubicacionIncidencias.isEmpty() && ubicacionIncidencias.get(0).getUbicacion().getLugar() != null) ? ubicacionIncidencias.get(0).getUbicacion().getLugar().getNombreLugar() : "";
            coleccion.put("tipoLugar", tipoLugar);
            coleccion.put("nombreLugar", nombreLugar);

            coleccion.put("estado", (incidencia.getEstado() != null && incidencia.getEstado().getNombre() != null) ? incidencia.getEstado().getNombre() : "");

            //Date fechaAutorizacion = incidencia.getFechaAutorizacion();
            //LocalDate fechaAutorizacionLocal = fechaAutorizacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           // String fechaAutorizacionFormateada = fechaAutorizacionLocal.format(pattern);

            coleccion.put("fechaAutorizacion", (incidencia.getFechaAutorizacion() != null) ? incidencia.getFechaAutorizacion() : "");
            coleccion.put("situacion", "EN PROCESO"); // Este valor parece ser una constante, por lo que no necesita una condición
            coleccion.put("descripcionIncidencia", (incidencia.getDescripcionIncidencia() != null) ? incidencia.getDescripcionIncidencia() : "");

            incidenciasList.add(coleccion);
        }

        return incidenciasList;
    }


}
