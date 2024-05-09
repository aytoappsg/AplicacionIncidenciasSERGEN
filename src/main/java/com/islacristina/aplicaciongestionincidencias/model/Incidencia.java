package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "incidencia")
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
    @Column(name = "usuario")
    private int usuario;
    @Column(name = "estado_incidencia")
    private Integer estadoIncidencia; // Cambiado de int a Integer
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
    @Column(name = "coordinador")
    private Integer coordinador;
    @Column(name= "autor")
    private String autor;
    @Column(name = "resumen")
    private String resumen;

    // Campos adicionales
    private String tipo;
    private String aplicadoA;
    private List<String> archivos;
    @ManyToOne
    private Procedencia procedencia;

    public LocalDate getFechaNotificacion() {
        return fechaNotificacion.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    @ManyToOne
    private EstadoIncidencia estado;

    public LocalDate getFechaServiciosGenerales() {
        return fechaServiciosGenerales.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    @ManyToOne
    @JoinColumn(name = "tercero")
    private Tercero tercero;

    @Override
    public String toString() {
        return "Incidencia{" +
                "numOrden=" + numOrden +
                ", estadoIncidencia=" + estadoIncidencia +
                ", autor='" + autor + '\'' +
                ", resumen='" + resumen + '\'' +
                '}';
    }
    // getters and setters...
    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Usuario coordinador;


    @OneToMany(mappedBy = "incidencia")
    private List<UbicacionIncidencia> ubicacionesIncidencias;
}