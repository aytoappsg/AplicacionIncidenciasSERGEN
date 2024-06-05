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
@Entity(name = "ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_lugar")
    private Lugar lugar;

    @ManyToOne
    @JoinColumn(name = "id_tipo_lugar")
    private TipoLugar tipoLugar;

    //LISTA DE UBICACION_INCIDENCIA
    @OneToMany(mappedBy = "ubicacion")
    private List<UbicacionIncidencia> ubicacionesIncidencias;

}
