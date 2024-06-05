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
@Entity(name = "encargado")
public class Encargado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encargado")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido1")
    private String apellidoUno;

    @Column(name = "apellido2")
    private String apellidoDos;

    @Column(name = "telefono")
    private String telefono;

    @ManyToOne
    private GrupoTrabajo grupoTrabajo;

    @OneToMany(mappedBy = "encargado")
    private List<HistoricoProcede> historicosProcede;
}

