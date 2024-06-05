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
@Table(name = "ubicacion_incidencia")
public class UbicacionIncidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion_incidencia")
    private int id;

    @ManyToOne
    @JoinColumn(name = "incidencia")
    private Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "ubicacion")
    private Ubicacion ubicacion;
}
