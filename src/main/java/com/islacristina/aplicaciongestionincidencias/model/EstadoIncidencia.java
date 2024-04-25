package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EstadoIncidencia {

    @Id
    @Column(name = "id_estado_incidencia")
    private int id;

    @Column(name = "estado_incidencia")
    private String nombre;

}
