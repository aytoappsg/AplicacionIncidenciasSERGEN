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
@Table(name = "destinatario")
public class Destinatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destinatario")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "destinatario")
    @JoinTable(
            name = "destinatarios_derivada",
            joinColumns = @JoinColumn(name = "id_destinatario"),
            inverseJoinColumns = @JoinColumn(name = "id_historico_derivada")
    )
    private List<HistoricoDerivada> historicoDerivadas;


}
