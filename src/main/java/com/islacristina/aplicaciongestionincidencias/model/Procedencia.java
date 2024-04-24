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
public class Procedencia {
    @Id
    private int idProcedencia;

    @Column(name = "tipo_procedencia")
    private String tipoProcedencia;
}
