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
@Entity(name = "estado_incidencia")
public class EstadoIncidencia {

    @Id
    @Column(name = "id_estado_incidencia")
    private int id;

    @Column(name = "estado_incidencia")
    private String nombre;

    @OneToMany(mappedBy = "estadoIncidencia")
    private List<Incidencia> incidencias;


}
