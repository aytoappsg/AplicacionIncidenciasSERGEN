package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    @Column(name = "procedencia_incidencia")
    private int procedenciaIncidencia;
    @Column(name = "nuestra_referencia")
    private String nuestraIncidencia;
    @Column(name = "num_registro_ayuntamiento")
    private String numRegistroAyuntamiento;
    @Column(name = "num_expediente_ayuntamiento")
    private String numExpedienteAyuntamiento;
    @Column(name = "tercero")
    private int tercero;
    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;
    @Column(name = "fecha_servicios_generales")
    private Date fechaServiciosGenerales;
    @Column(name = "descripcion_incidencia")
    private String descripcionIncidencia;
    @Column(name = "usuario")
    private int usuario;
    @Column(name = "estado_incidencia")
    private int estadoIncidencia;
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
    private int coordinador;

    // getters and setters
    /*
    public int getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    public int getProcedenciaIncidencia() {
        return procedenciaIncidencia;
    }

    public void setProcedenciaIncidencia(int procedenciaIncidencia) {
        this.procedenciaIncidencia = procedenciaIncidencia;
    }

    public String getNuestraIncidencia() {
        return nuestraIncidencia;
    }

    public void setNuestraIncidencia(String nuestraIncidencia) {
        this.nuestraIncidencia = nuestraIncidencia;
    }

    public String getNumRegistroAyuntamiento() {
        return numRegistroAyuntamiento;
    }

    public void setNumRegistroAyuntamiento(String numRegistroAyuntamiento) {
        this.numRegistroAyuntamiento = numRegistroAyuntamiento;
    }

    public String getNumExpedienteAyuntamiento() {
        return numExpedienteAyuntamiento;
    }

    public void setNumExpedienteAyuntamiento(String numExpedienteAyuntamiento) {
        this.numExpedienteAyuntamiento = numExpedienteAyuntamiento;
    }

    public int getTercero() {
        return tercero;
    }

    public void setTercero(int tercero) {
        this.tercero = tercero;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaServiciosGenerales() {
        return fechaServiciosGenerales;
    }

    public void setFechaServiciosGenerales(Date fechaServiciosGenerales) {
        this.fechaServiciosGenerales = fechaServiciosGenerales;
    }

    public String getDescripcionIncidencia() {
        return descripcionIncidencia;
    }

    public void setDescripcionIncidencia(String descripcionIncidencia) {
        this.descripcionIncidencia = descripcionIncidencia;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getEstadoIncidencia() {
        return estadoIncidencia;
    }

    public void setEstadoIncidencia(int estadoIncidencia) {
        this.estadoIncidencia = estadoIncidencia;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getMotivoNoProcede() {
        return motivoNoProcede;
    }

    public void setMotivoNoProcede(String motivoNoProcede) {
        this.motivoNoProcede = motivoNoProcede;
    }

    public String getMotivoSuspendida() {
        return motivoSuspendida;
    }

    public void setMotivoSuspendida(String motivoSuspendida) {
        this.motivoSuspendida = motivoSuspendida;
    }

    public String getMotivoPendiente() {
        return motivoPendiente;
    }

    public void setMotivoPendiente(String motivoPendiente) {
        this.motivoPendiente = motivoPendiente;
    }

    public int getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(int coordinador) {
        this.coordinador = coordinador;
    }
     */
}