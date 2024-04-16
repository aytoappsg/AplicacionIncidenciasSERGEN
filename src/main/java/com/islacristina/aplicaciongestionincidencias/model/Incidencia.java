package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
public class Incidencia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "num_orden")
    private int numOrden;
    @Basic
    @Column(name = "procedencia_incidencia")
    private int procedenciaIncidencia;
    @Basic
    @Column(name = "nuestra_referencia")
    private String nuestraReferencia;
    @Basic
    @Column(name = "num_registro_ayuntamiento")
    private String numRegistroAyuntamiento;
    @Basic
    @Column(name = "num_expediente_ayuntamiento")
    private String numExpedienteAyuntamiento;
    @Basic
    @Column(name = "tercero")
    private int tercero;
    @Basic
    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;
    @Basic
    @Column(name = "fecha_servicios_generales")
    private Date fechaServiciosGenerales;
    @Basic
    @Column(name = "descripcion_incidencia")
    private String descripcionIncidencia;
    @Basic
    @Column(name = "usuario")
    private int usuario;
    @Basic
    @Column(name = "estado_incidencia")
    private Integer estadoIncidencia;
    @Basic
    @Column(name = "fecha_autorizacion")
    private Date fechaAutorizacion;
    @Basic
    @Column(name = "observacion")
    private String observacion;
    @Basic
    @Column(name = "motivo_no_procede")
    private String motivoNoProcede;
    @Basic
    @Column(name = "motivo_suspendia")
    private String motivoSuspendia;
    @Basic
    @Column(name = "motivo_pendiente")
    private String motivoPendiente;
    @Basic
    @Column(name = "coordinador")
    private Integer coordinador;
    @OneToMany(mappedBy = "incidenciaByIncidencia")
    private Collection<HistoricoDerivada> historicoDerivadasByNumOrden;
    @OneToMany(mappedBy = "incidenciaByIncidencia")
    private Collection<HistoricoProcede> historicoProcedesByNumOrden;
    @ManyToOne
    @JoinColumn(name = "procedencia_incidencia", referencedColumnName = "id_procedencia", nullable = false)
    private Procedencia procedenciaByProcedenciaIncidencia;
    @ManyToOne
    @JoinColumn(name = "tercero", referencedColumnName = "id_tercero", nullable = false)
    private Tercero terceroByTercero;
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioByUsuario;
    @ManyToOne
    @JoinColumn(name = "estado_incidencia", referencedColumnName = "id_estado_incidencia")
    private EstadoIncidencia estadoIncidenciaByEstadoIncidencia;
    @ManyToOne
    @JoinColumn(name = "coordinador", referencedColumnName = "id_usuario")
    private Usuario usuarioByCoordinador;
    @OneToMany(mappedBy = "incidenciaByIncidencia")
    private Collection<UbicacionIncidencia> ubicacionIncidenciasByNumOrden;

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

    public String getNuestraReferencia() {
        return nuestraReferencia;
    }

    public void setNuestraReferencia(String nuestraReferencia) {
        this.nuestraReferencia = nuestraReferencia;
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

    public Integer getEstadoIncidencia() {
        return estadoIncidencia;
    }

    public void setEstadoIncidencia(Integer estadoIncidencia) {
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

    public String getMotivoSuspendia() {
        return motivoSuspendia;
    }

    public void setMotivoSuspendia(String motivoSuspendia) {
        this.motivoSuspendia = motivoSuspendia;
    }

    public String getMotivoPendiente() {
        return motivoPendiente;
    }

    public void setMotivoPendiente(String motivoPendiente) {
        this.motivoPendiente = motivoPendiente;
    }

    public Integer getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Integer coordinador) {
        this.coordinador = coordinador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Incidencia that = (Incidencia) o;

        if (numOrden != that.numOrden) return false;
        if (procedenciaIncidencia != that.procedenciaIncidencia) return false;
        if (tercero != that.tercero) return false;
        if (usuario != that.usuario) return false;
        if (nuestraReferencia != null ? !nuestraReferencia.equals(that.nuestraReferencia) : that.nuestraReferencia != null)
            return false;
        if (numRegistroAyuntamiento != null ? !numRegistroAyuntamiento.equals(that.numRegistroAyuntamiento) : that.numRegistroAyuntamiento != null)
            return false;
        if (numExpedienteAyuntamiento != null ? !numExpedienteAyuntamiento.equals(that.numExpedienteAyuntamiento) : that.numExpedienteAyuntamiento != null)
            return false;
        if (fechaNotificacion != null ? !fechaNotificacion.equals(that.fechaNotificacion) : that.fechaNotificacion != null)
            return false;
        if (fechaServiciosGenerales != null ? !fechaServiciosGenerales.equals(that.fechaServiciosGenerales) : that.fechaServiciosGenerales != null)
            return false;
        if (descripcionIncidencia != null ? !descripcionIncidencia.equals(that.descripcionIncidencia) : that.descripcionIncidencia != null)
            return false;
        if (estadoIncidencia != null ? !estadoIncidencia.equals(that.estadoIncidencia) : that.estadoIncidencia != null)
            return false;
        if (fechaAutorizacion != null ? !fechaAutorizacion.equals(that.fechaAutorizacion) : that.fechaAutorizacion != null)
            return false;
        if (observacion != null ? !observacion.equals(that.observacion) : that.observacion != null) return false;
        if (motivoNoProcede != null ? !motivoNoProcede.equals(that.motivoNoProcede) : that.motivoNoProcede != null)
            return false;
        if (motivoSuspendia != null ? !motivoSuspendia.equals(that.motivoSuspendia) : that.motivoSuspendia != null)
            return false;
        if (motivoPendiente != null ? !motivoPendiente.equals(that.motivoPendiente) : that.motivoPendiente != null)
            return false;
        if (coordinador != null ? !coordinador.equals(that.coordinador) : that.coordinador != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numOrden;
        result = 31 * result + procedenciaIncidencia;
        result = 31 * result + (nuestraReferencia != null ? nuestraReferencia.hashCode() : 0);
        result = 31 * result + (numRegistroAyuntamiento != null ? numRegistroAyuntamiento.hashCode() : 0);
        result = 31 * result + (numExpedienteAyuntamiento != null ? numExpedienteAyuntamiento.hashCode() : 0);
        result = 31 * result + tercero;
        result = 31 * result + (fechaNotificacion != null ? fechaNotificacion.hashCode() : 0);
        result = 31 * result + (fechaServiciosGenerales != null ? fechaServiciosGenerales.hashCode() : 0);
        result = 31 * result + (descripcionIncidencia != null ? descripcionIncidencia.hashCode() : 0);
        result = 31 * result + usuario;
        result = 31 * result + (estadoIncidencia != null ? estadoIncidencia.hashCode() : 0);
        result = 31 * result + (fechaAutorizacion != null ? fechaAutorizacion.hashCode() : 0);
        result = 31 * result + (observacion != null ? observacion.hashCode() : 0);
        result = 31 * result + (motivoNoProcede != null ? motivoNoProcede.hashCode() : 0);
        result = 31 * result + (motivoSuspendia != null ? motivoSuspendia.hashCode() : 0);
        result = 31 * result + (motivoPendiente != null ? motivoPendiente.hashCode() : 0);
        result = 31 * result + (coordinador != null ? coordinador.hashCode() : 0);
        return result;
    }

    public Collection<HistoricoDerivada> getHistoricoDerivadasByNumOrden() {
        return historicoDerivadasByNumOrden;
    }

    public void setHistoricoDerivadasByNumOrden(Collection<HistoricoDerivada> historicoDerivadasByNumOrden) {
        this.historicoDerivadasByNumOrden = historicoDerivadasByNumOrden;
    }

    public Collection<HistoricoProcede> getHistoricoProcedesByNumOrden() {
        return historicoProcedesByNumOrden;
    }

    public void setHistoricoProcedesByNumOrden(Collection<HistoricoProcede> historicoProcedesByNumOrden) {
        this.historicoProcedesByNumOrden = historicoProcedesByNumOrden;
    }

    public Procedencia getProcedenciaByProcedenciaIncidencia() {
        return procedenciaByProcedenciaIncidencia;
    }

    public void setProcedenciaByProcedenciaIncidencia(Procedencia procedenciaByProcedenciaIncidencia) {
        this.procedenciaByProcedenciaIncidencia = procedenciaByProcedenciaIncidencia;
    }

    public Tercero getTerceroByTercero() {
        return terceroByTercero;
    }

    public void setTerceroByTercero(Tercero terceroByTercero) {
        this.terceroByTercero = terceroByTercero;
    }

    public Usuario getUsuarioByUsuario() {
        return usuarioByUsuario;
    }

    public void setUsuarioByUsuario(Usuario usuarioByUsuario) {
        this.usuarioByUsuario = usuarioByUsuario;
    }

    public EstadoIncidencia getEstadoIncidenciaByEstadoIncidencia() {
        return estadoIncidenciaByEstadoIncidencia;
    }

    public void setEstadoIncidenciaByEstadoIncidencia(EstadoIncidencia estadoIncidenciaByEstadoIncidencia) {
        this.estadoIncidenciaByEstadoIncidencia = estadoIncidenciaByEstadoIncidencia;
    }

    public Usuario getUsuarioByCoordinador() {
        return usuarioByCoordinador;
    }

    public void setUsuarioByCoordinador(Usuario usuarioByCoordinador) {
        this.usuarioByCoordinador = usuarioByCoordinador;
    }

    public Collection<UbicacionIncidencia> getUbicacionIncidenciasByNumOrden() {
        return ubicacionIncidenciasByNumOrden;
    }

    public void setUbicacionIncidenciasByNumOrden(Collection<UbicacionIncidencia> ubicacionIncidenciasByNumOrden) {
        this.ubicacionIncidenciasByNumOrden = ubicacionIncidenciasByNumOrden;
    }
}
