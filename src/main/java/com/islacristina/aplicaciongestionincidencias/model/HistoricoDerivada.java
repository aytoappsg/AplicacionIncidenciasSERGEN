package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "historico_derivada")
public class HistoricoDerivada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_derivada")
    private int id;

    @Column(name = "fecha_derivacion")
    private Date fechaDerivacion;

    @ManyToOne
    private MedioDerivacion medioDerivacion;

    @OneToMany(mappedBy = "historicoDerivada")
    private List<DestinatarioDerivada> destinatariosDerivadas;


}
