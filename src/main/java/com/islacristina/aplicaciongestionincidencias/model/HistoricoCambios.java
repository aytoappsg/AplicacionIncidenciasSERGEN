package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "historico_cambios")
public class HistoricoCambios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_cambios")
    private int id;

    @Column(name = "estado_anterior")
    private String estadoAnterior;

    @Column(name = "nuevo_estado")
    private float nuevoEstado;

    @Column(name = "fecha_cambio")
    private Date fechaCambio;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private HistoricoProcede historicoProcede;

    //atributo


}
