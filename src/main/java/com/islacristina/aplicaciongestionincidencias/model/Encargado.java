package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Encargado {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_encargado")
    private int idEncargado;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "apellido1")
    private String apellido1;
    @Basic
    @Column(name = "apellido2")
    private String apellido2;
    @Basic
    @Column(name = "telefono")
    private String telefono;
    @Basic
    @Column(name = "id_grupo_trabajo")
    private int idGrupoTrabajo;
    @ManyToOne
    @JoinColumn(name = "id_grupo_trabajo", referencedColumnName = "id_grupo_trabajo", nullable = false)
    private GrupoTrabajo grupoTrabajoByIdGrupoTrabajo;
    @OneToMany(mappedBy = "encargadoByEncargado")
    private Collection<HistoricoProcede> historicoProcedesByIdEncargado;

    public int getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdGrupoTrabajo() {
        return idGrupoTrabajo;
    }

    public void setIdGrupoTrabajo(int idGrupoTrabajo) {
        this.idGrupoTrabajo = idGrupoTrabajo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Encargado encargado = (Encargado) o;

        if (idEncargado != encargado.idEncargado) return false;
        if (idGrupoTrabajo != encargado.idGrupoTrabajo) return false;
        if (nombre != null ? !nombre.equals(encargado.nombre) : encargado.nombre != null) return false;
        if (apellido1 != null ? !apellido1.equals(encargado.apellido1) : encargado.apellido1 != null) return false;
        if (apellido2 != null ? !apellido2.equals(encargado.apellido2) : encargado.apellido2 != null) return false;
        if (telefono != null ? !telefono.equals(encargado.telefono) : encargado.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEncargado;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido1 != null ? apellido1.hashCode() : 0);
        result = 31 * result + (apellido2 != null ? apellido2.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + idGrupoTrabajo;
        return result;
    }

    public GrupoTrabajo getGrupoTrabajoByIdGrupoTrabajo() {
        return grupoTrabajoByIdGrupoTrabajo;
    }

    public void setGrupoTrabajoByIdGrupoTrabajo(GrupoTrabajo grupoTrabajoByIdGrupoTrabajo) {
        this.grupoTrabajoByIdGrupoTrabajo = grupoTrabajoByIdGrupoTrabajo;
    }

    public Collection<HistoricoProcede> getHistoricoProcedesByIdEncargado() {
        return historicoProcedesByIdEncargado;
    }

    public void setHistoricoProcedesByIdEncargado(Collection<HistoricoProcede> historicoProcedesByIdEncargado) {
        this.historicoProcedesByIdEncargado = historicoProcedesByIdEncargado;
    }
}
