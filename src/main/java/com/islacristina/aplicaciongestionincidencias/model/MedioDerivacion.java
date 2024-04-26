package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medio_derivacion")
public class MedioDerivacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medio_derivacion")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "medioDerivacion")
    private List<HistoricoDerivada> historicoDerivadas;
}