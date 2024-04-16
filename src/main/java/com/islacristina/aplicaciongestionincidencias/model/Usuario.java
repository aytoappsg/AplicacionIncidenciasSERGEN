package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "contrasena")
    private String contrasena;
    @Basic
    @Column(name = "rol")
    private String rol;
    @OneToMany(mappedBy = "usuarioByUsuario")
    private Collection<HistoricoCambios> historicoCambiosByIdUsuario;
    @OneToMany(mappedBy = "usuarioByUsuario")
    private Collection<Incidencia> incidenciasByIdUsuario;
    @OneToMany(mappedBy = "usuarioByCoordinador")
    private Collection<Incidencia> incidenciasByIdUsuario_0;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (idUsuario != usuario.idUsuario) return false;
        if (nombre != null ? !nombre.equals(usuario.nombre) : usuario.nombre != null) return false;
        if (contrasena != null ? !contrasena.equals(usuario.contrasena) : usuario.contrasena != null) return false;
        if (rol != null ? !rol.equals(usuario.rol) : usuario.rol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUsuario;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (contrasena != null ? contrasena.hashCode() : 0);
        result = 31 * result + (rol != null ? rol.hashCode() : 0);
        return result;
    }

    public Collection<HistoricoCambios> getHistoricoCambiosByIdUsuario() {
        return historicoCambiosByIdUsuario;
    }

    public void setHistoricoCambiosByIdUsuario(Collection<HistoricoCambios> historicoCambiosByIdUsuario) {
        this.historicoCambiosByIdUsuario = historicoCambiosByIdUsuario;
    }

    public Collection<Incidencia> getIncidenciasByIdUsuario() {
        return incidenciasByIdUsuario;
    }

    public void setIncidenciasByIdUsuario(Collection<Incidencia> incidenciasByIdUsuario) {
        this.incidenciasByIdUsuario = incidenciasByIdUsuario;
    }

    public Collection<Incidencia> getIncidenciasByIdUsuario_0() {
        return incidenciasByIdUsuario_0;
    }

    public void setIncidenciasByIdUsuario_0(Collection<Incidencia> incidenciasByIdUsuario_0) {
        this.incidenciasByIdUsuario_0 = incidenciasByIdUsuario_0;
    }
}
