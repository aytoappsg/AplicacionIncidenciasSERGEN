package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Tercero {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tercero")
    private int idTercero;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "dni_nif")
    private String dniNif;
    @Basic
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(mappedBy = "terceroByTercero")
    private Collection<Incidencia> incidenciasByIdTercero;

    public int getIdTercero() {
        return idTercero;
    }

    public void setIdTercero(int idTercero) {
        this.idTercero = idTercero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDniNif() {
        return dniNif;
    }

    public void setDniNif(String dniNif) {
        this.dniNif = dniNif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tercero tercero = (Tercero) o;

        if (idTercero != tercero.idTercero) return false;
        if (nombre != null ? !nombre.equals(tercero.nombre) : tercero.nombre != null) return false;
        if (email != null ? !email.equals(tercero.email) : tercero.email != null) return false;
        if (dniNif != null ? !dniNif.equals(tercero.dniNif) : tercero.dniNif != null) return false;
        if (telefono != null ? !telefono.equals(tercero.telefono) : tercero.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTercero;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (dniNif != null ? dniNif.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }

    public Collection<Incidencia> getIncidenciasByIdTercero() {
        return incidenciasByIdTercero;
    }

    public void setIncidenciasByIdTercero(Collection<Incidencia> incidenciasByIdTercero) {
        this.incidenciasByIdTercero = incidenciasByIdTercero;
    }
}
