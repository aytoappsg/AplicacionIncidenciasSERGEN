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
    private Lugar lugar;

    @ManyToOne
    private TipoLugar tipoLugar;

    @ManyToMany(mappedBy = "ubicaciones")
    @JoinTable(
            name = "ubicacion_incidencia",
            joinColumns = @JoinColumn(name = "incidencia"),
            inverseJoinColumns = @JoinColumn(name = "ubicacion")
    )
    private List<Incidencia> incidencias;
}
