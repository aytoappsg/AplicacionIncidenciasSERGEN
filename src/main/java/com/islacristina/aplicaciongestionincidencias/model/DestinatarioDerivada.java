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
@Table(name = "destinatario_derivada")
public class DestinatarioDerivada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destinatarios_derivada")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_destinataio")
    private Destinatario destinatario;

    @ManyToOne
    @JoinColumn(name = "id_historico_derivada")
    private HistoricoDerivada historicoDerivada;


}
