package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Ubicacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ubicacion")
    private int idUbicacion;
    @Basic
    @Column(name = "id_tipo_lugar")
    private int idTipoLugar;
    @Basic
    @Column(name = "id_lugar")
    private int idLugar;
    @ManyToOne
    @JoinColumn(name = "id_tipo_lugar", referencedColumnName = "id_tipo_lugar", nullable = false)
    private TipoLugar tipoLugarByIdTipoLugar;
    @ManyToOne
    @JoinColumn(name = "id_lugar", referencedColumnName = "id_lugar", nullable = false)
    private Lugar lugarByIdLugar;
    @OneToMany(mappedBy = "ubicacionByUbicacion")
    private Collection<UbicacionIncidencia> ubicacionIncidenciasByIdUbicacion;

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public int getIdTipoLugar() {
        return idTipoLugar;
    }

    public void setIdTipoLugar(int idTipoLugar) {
        this.idTipoLugar = idTipoLugar;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ubicacion ubicacion = (Ubicacion) o;

        if (idUbicacion != ubicacion.idUbicacion) return false;
        if (idTipoLugar != ubicacion.idTipoLugar) return false;
        if (idLugar != ubicacion.idLugar) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUbicacion;
        result = 31 * result + idTipoLugar;
        result = 31 * result + idLugar;
        return result;
    }

    public TipoLugar getTipoLugarByIdTipoLugar() {
        return tipoLugarByIdTipoLugar;
    }

    public void setTipoLugarByIdTipoLugar(TipoLugar tipoLugarByIdTipoLugar) {
        this.tipoLugarByIdTipoLugar = tipoLugarByIdTipoLugar;
    }

    public Lugar getLugarByIdLugar() {
        return lugarByIdLugar;
    }

    public void setLugarByIdLugar(Lugar lugarByIdLugar) {
        this.lugarByIdLugar = lugarByIdLugar;
    }

    public Collection<UbicacionIncidencia> getUbicacionIncidenciasByIdUbicacion() {
        return ubicacionIncidenciasByIdUbicacion;
    }

    public void setUbicacionIncidenciasByIdUbicacion(Collection<UbicacionIncidencia> ubicacionIncidenciasByIdUbicacion) {
        this.ubicacionIncidenciasByIdUbicacion = ubicacionIncidenciasByIdUbicacion;
    }
}
