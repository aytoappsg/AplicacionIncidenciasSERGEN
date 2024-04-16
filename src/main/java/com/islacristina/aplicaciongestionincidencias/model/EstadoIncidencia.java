package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "estado_incidencia", schema = "serviciosgenerales", catalog = "")
public class EstadoIncidencia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_estado_incidencia")
    private int idEstadoIncidencia;
    @Basic
    @Column(name = "estado_incidencia")
    private String estadoIncidencia;
    @OneToMany(mappedBy = "estadoIncidenciaByEstadoIncidencia")
    private Collection<Incidencia> incidenciasByIdEstadoIncidencia;

    public int getIdEstadoIncidencia() {
        return idEstadoIncidencia;
    }

    public void setIdEstadoIncidencia(int idEstadoIncidencia) {
        this.idEstadoIncidencia = idEstadoIncidencia;
    }

    public String getEstadoIncidencia() {
        return estadoIncidencia;
    }

    public void setEstadoIncidencia(String estadoIncidencia) {
        this.estadoIncidencia = estadoIncidencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoIncidencia that = (EstadoIncidencia) o;

        if (idEstadoIncidencia != that.idEstadoIncidencia) return false;
        if (estadoIncidencia != null ? !estadoIncidencia.equals(that.estadoIncidencia) : that.estadoIncidencia != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEstadoIncidencia;
        result = 31 * result + (estadoIncidencia != null ? estadoIncidencia.hashCode() : 0);
        return result;
    }

    public Collection<Incidencia> getIncidenciasByIdEstadoIncidencia() {
        return incidenciasByIdEstadoIncidencia;
    }

    public void setIncidenciasByIdEstadoIncidencia(Collection<Incidencia> incidenciasByIdEstadoIncidencia) {
        this.incidenciasByIdEstadoIncidencia = incidenciasByIdEstadoIncidencia;
    }
}
