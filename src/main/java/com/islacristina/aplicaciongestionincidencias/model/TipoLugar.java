package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "tipo_lugar", schema = "serviciosgenerales", catalog = "")
public class TipoLugar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tipo_lugar")
    private int idTipoLugar;
    @Basic
    @Column(name = "tipo_lugar")
    private String tipoLugar;
    @OneToMany(mappedBy = "tipoLugarByIdTipoLugar")
    private Collection<Ubicacion> ubicacionsByIdTipoLugar;

    public int getIdTipoLugar() {
        return idTipoLugar;
    }

    public void setIdTipoLugar(int idTipoLugar) {
        this.idTipoLugar = idTipoLugar;
    }

    public String getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(String tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoLugar tipoLugar1 = (TipoLugar) o;

        if (idTipoLugar != tipoLugar1.idTipoLugar) return false;
        if (tipoLugar != null ? !tipoLugar.equals(tipoLugar1.tipoLugar) : tipoLugar1.tipoLugar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipoLugar;
        result = 31 * result + (tipoLugar != null ? tipoLugar.hashCode() : 0);
        return result;
    }

    public Collection<Ubicacion> getUbicacionsByIdTipoLugar() {
        return ubicacionsByIdTipoLugar;
    }

    public void setUbicacionsByIdTipoLugar(Collection<Ubicacion> ubicacionsByIdTipoLugar) {
        this.ubicacionsByIdTipoLugar = ubicacionsByIdTipoLugar;
    }
}
