package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "historico_derivada", schema = "serviciosgenerales", catalog = "")
public class HistoricoDerivada {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_historico_derivada")
    private int idHistoricoDerivada;
    @Basic
    @Column(name = "medio_derivacion")
    private int medioDerivacion;
    @Basic
    @Column(name = "fecha_derivacion")
    private Date fechaDerivacion;
    @Basic
    @Column(name = "incidencia")
    private int incidencia;
    @Basic
    @Column(name = "id_destinatario")
    private int idDestinatario;
    @ManyToOne
    @JoinColumn(name = "medio_derivacion", referencedColumnName = "id_medio_derivacion", nullable = false)
    private MedioDerivacion medioDerivacionByMedioDerivacion;
    @ManyToOne
    @JoinColumn(name = "incidencia", referencedColumnName = "num_orden", nullable = false)
    private Incidencia incidenciaByIncidencia;
    @ManyToOne
    @JoinColumn(name = "id_destinatario", referencedColumnName = "id_destinatario", nullable = false)
    private Destinatario destinatarioByIdDestinatario;

    public int getIdHistoricoDerivada() {
        return idHistoricoDerivada;
    }

    public void setIdHistoricoDerivada(int idHistoricoDerivada) {
        this.idHistoricoDerivada = idHistoricoDerivada;
    }

    public int getMedioDerivacion() {
        return medioDerivacion;
    }

    public void setMedioDerivacion(int medioDerivacion) {
        this.medioDerivacion = medioDerivacion;
    }

    public Date getFechaDerivacion() {
        return fechaDerivacion;
    }

    public void setFechaDerivacion(Date fechaDerivacion) {
        this.fechaDerivacion = fechaDerivacion;
    }

    public int getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(int incidencia) {
        this.incidencia = incidencia;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoricoDerivada that = (HistoricoDerivada) o;

        if (idHistoricoDerivada != that.idHistoricoDerivada) return false;
        if (medioDerivacion != that.medioDerivacion) return false;
        if (incidencia != that.incidencia) return false;
        if (idDestinatario != that.idDestinatario) return false;
        if (fechaDerivacion != null ? !fechaDerivacion.equals(that.fechaDerivacion) : that.fechaDerivacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHistoricoDerivada;
        result = 31 * result + medioDerivacion;
        result = 31 * result + (fechaDerivacion != null ? fechaDerivacion.hashCode() : 0);
        result = 31 * result + incidencia;
        result = 31 * result + idDestinatario;
        return result;
    }

    public MedioDerivacion getMedioDerivacionByMedioDerivacion() {
        return medioDerivacionByMedioDerivacion;
    }

    public void setMedioDerivacionByMedioDerivacion(MedioDerivacion medioDerivacionByMedioDerivacion) {
        this.medioDerivacionByMedioDerivacion = medioDerivacionByMedioDerivacion;
    }

    public Incidencia getIncidenciaByIncidencia() {
        return incidenciaByIncidencia;
    }

    public void setIncidenciaByIncidencia(Incidencia incidenciaByIncidencia) {
        this.incidenciaByIncidencia = incidenciaByIncidencia;
    }

    public Destinatario getDestinatarioByIdDestinatario() {
        return destinatarioByIdDestinatario;
    }

    public void setDestinatarioByIdDestinatario(Destinatario destinatarioByIdDestinatario) {
        this.destinatarioByIdDestinatario = destinatarioByIdDestinatario;
    }
}
