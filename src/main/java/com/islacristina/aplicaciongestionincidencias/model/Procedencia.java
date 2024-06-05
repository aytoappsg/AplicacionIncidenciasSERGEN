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
@Entity
public class Procedencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_procedencia")
    private int idProcedencia;

    @Column(name = "tipo_procedencia")
    private String tipoProcedencia;

    @Column(name = "prefijo_procedencia")
    private String prefijoProcedencia;

    @Column(name = "metodo_validacion")
    private String metodoValidacion;

    @OneToMany(mappedBy = "procedencia")
    private List<Incidencia> incidencias;

}
