package com.islacristina.aplicaciongestionincidencias.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuario")
public class Usuario {

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

    @OneToMany(mappedBy = "historico_cambios")
    private List<HistoricoCambios> historicoCambios;

    @OneToMany(mappedBy = "usuario")
    private List<Incidencia> incidencias;

    @OneToMany(mappedBy = "coordinador")
    private List<Incidencia> incidenciasCoordinadores;


}
