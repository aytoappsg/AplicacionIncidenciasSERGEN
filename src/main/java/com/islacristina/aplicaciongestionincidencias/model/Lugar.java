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
@Entity(name="lugar")
public class Lugar {

    @Id
    @Column(name = "id_lugar")
    private int idLugar;

    @Column(name = "nombre_lugar")
    private String nombreLugar;

    @OneToMany(mappedBy = "lugar")
    private List<Ubicacion> ubicaciones;

}
