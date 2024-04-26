package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_orden")
    private int numOrden;
    @Column(name = "nuestra_referencia")
    private String nuestraIncidencia;
    @Column(name = "num_registro_ayuntamiento")
    private String numRegistroAyuntamiento;
    @Column(name = "num_expediente_ayuntamiento")
    private String numExpedienteAyuntamiento;
    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;
    @Column(name = "fecha_servicios_generales")
    private Date fechaServiciosGenerales;
    @Column(name = "descripcion_incidencia")
    private String descripcionIncidencia;
    @Column(name = "fecha_autorización")
    private Date fechaAutorizacion;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "motivo_no_procede")
    private String motivoNoProcede;
    @Column(name = "motivo_suspendia")
    private String motivoSuspendida;
    @Column(name = "motivo_pendiente")
    private String motivoPendiente;

    @ManyToOne
    private Procedencia procedencia;

    @ManyToOne
    private EstadoIncidencia estado;

    @ManyToOne
    private Tercero terceroa;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Usuario coordinador;

    @ManyToMany(mappedBy = "ubicacion")
    private List<Ubicacion> ubicaciones;


}