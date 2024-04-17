package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numOrden;

    private int procedenciaIncidencia;

    private String nuestraIncidencia;

    private String numRegistroAyuntamiento;

    private String numExpedienteAyuntamiento;

    private int tercero;

    private Date fechaNotificacion;

    private Date fechaServiciosGenerales;

    private String descripcionIncidencia;

    private int usuario;

    private int estadoIncidencia;

    private Date fechaAutorizacion;

    private String observacion;

    private String motivoNoProcede;

    private String motivoSuspendida;

    private String motivoPendiente;

    private int coordinador;

    // getters and setters
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
}