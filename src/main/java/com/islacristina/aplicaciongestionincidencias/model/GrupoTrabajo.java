package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "grupo_trabajo", schema = "serviciosgenerales", catalog = "")
public class GrupoTrabajo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_grupo_trabajo")
    private int idGrupoTrabajo;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "grupoTrabajoByIdGrupoTrabajo")
    private Collection<Encargado> encargadosByIdGrupoTrabajo;

    public int getIdGrupoTrabajo() {
        return idGrupoTrabajo;
    }

    public void setIdGrupoTrabajo(int idGrupoTrabajo) {
        this.idGrupoTrabajo = idGrupoTrabajo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrupoTrabajo that = (GrupoTrabajo) o;

        if (idGrupoTrabajo != that.idGrupoTrabajo) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGrupoTrabajo;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Collection<Encargado> getEncargadosByIdGrupoTrabajo() {
        return encargadosByIdGrupoTrabajo;
    }

    public void setEncargadosByIdGrupoTrabajo(Collection<Encargado> encargadosByIdGrupoTrabajo) {
        this.encargadosByIdGrupoTrabajo = encargadosByIdGrupoTrabajo;
    }
}
