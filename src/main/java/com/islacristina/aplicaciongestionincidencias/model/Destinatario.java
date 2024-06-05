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

    @OneToMany(mappedBy = "destinatario")
    private List<DestinatarioDerivada> destinatariosDerivadas;




}
