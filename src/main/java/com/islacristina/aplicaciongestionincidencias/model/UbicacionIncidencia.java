package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ubicacion_incidencia", schema = "serviciosgenerales", catalog = "")
public class UbicacionIncidencia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ubicacion_incidencia")
    private int idUbicacionIncidencia;
    @Basic
    @Column(name = "incidencia")
    private int incidencia;
    @Basic
    @Column(name = "ubicacion")
    private int ubicacion;
    @ManyToOne
    @JoinColumn(name = "incidencia", referencedColumnName = "num_orden", nullable = false)
    private Incidencia incidenciaByIncidencia;
    @ManyToOne
    @JoinColumn(name = "ubicacion", referencedColumnName = "id_ubicacion", nullable = false)
    private Ubicacion ubicacionByUbicacion;

    public int getIdUbicacionIncidencia() {
        return idUbicacionIncidencia;
    }

    public void setIdUbicacionIncidencia(int idUbicacionIncidencia) {
        this.idUbicacionIncidencia = idUbicacionIncidencia;
    }

    public int getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(int incidencia) {
        this.incidencia = incidencia;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UbicacionIncidencia that = (UbicacionIncidencia) o;

        if (idUbicacionIncidencia != that.idUbicacionIncidencia) return false;
        if (incidencia != that.incidencia) return false;
        if (ubicacion != that.ubicacion) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUbicacionIncidencia;
        result = 31 * result + incidencia;
        result = 31 * result + ubicacion;
        return result;
    }

    public Incidencia getIncidenciaByIncidencia() {
        return incidenciaByIncidencia;
    }

    public void setIncidenciaByIncidencia(Incidencia incidenciaByIncidencia) {
        this.incidenciaByIncidencia = incidenciaByIncidencia;
    }

    public Ubicacion getUbicacionByUbicacion() {
        return ubicacionByUbicacion;
    }

    public void setUbicacionByUbicacion(Ubicacion ubicacionByUbicacion) {
        this.ubicacionByUbicacion = ubicacionByUbicacion;
    }
}
