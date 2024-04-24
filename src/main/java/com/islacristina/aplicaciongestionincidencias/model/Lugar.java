package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lugar {

    @Id
    @Column(name = "id_lugar")
    private int idLugar;

    @Column(name = "nombre_lugar")
    private String nombreLugar;

}
