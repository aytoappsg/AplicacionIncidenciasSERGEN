package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Lugar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_lugar")
    private int idLugar;
    @Basic
    @Column(name = "nombre_lugar")
    private String nombreLugar;
    @OneToMany(mappedBy = "lugarByIdLugar")
    private Collection<Ubicacion> ubicacionsByIdLugar;

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lugar lugar = (Lugar) o;

        if (idLugar != lugar.idLugar) return false;
        if (nombreLugar != null ? !nombreLugar.equals(lugar.nombreLugar) : lugar.nombreLugar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLugar;
        result = 31 * result + (nombreLugar != null ? nombreLugar.hashCode() : 0);
        return result;
    }

    public Collection<Ubicacion> getUbicacionsByIdLugar() {
        return ubicacionsByIdLugar;
    }

    public void setUbicacionsByIdLugar(Collection<Ubicacion> ubicacionsByIdLugar) {
        this.ubicacionsByIdLugar = ubicacionsByIdLugar;
    }
}
