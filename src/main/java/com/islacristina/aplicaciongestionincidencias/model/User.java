package com.islacristina.aplicaciongestionincidencias.model;


import jakarta.persistence.*;

@Entity(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    @Column(name = "nombre")
    private String name;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "rol")
    private String role;

    public User() {
    }

    public User(String name, String contrasena, String role) {
        this.name = name;
        this.contrasena = contrasena;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
