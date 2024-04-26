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
@Entity(name = "historico_procede")
public class HistoricoProcede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_procede")
    private int id;

    @Column(name = "num_horas_estimadas")
    private float numHorasEstimadas;

    @Column(name = "num_trabajadores_estimados")
    private float numTrabajadoresEstimados;

    @Column(name = "num_horas")
    private float numHoras;

    @Column(name = "num_trabajadores")
    private float numTrabajadores;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_finalizado")
    private Date fechaFinzalizado;

    @Column(name = "propuesto")
    private Boolean propuesto;

    @Column(name = "enviado")
    private Boolean enviado;

    @Column(name = "pendiente")
    private Boolean pendiente;

    @Column(name = "terminado")
    private Boolean terminado;


    //RELACION CON ENCARGADO E INCIDENCIA


    @OneToMany(mappedBy = "tercero")
    private List<HistoricoCambios> historicoCambios;

    @ManyToOne
    @JoinColumn(name = "encargado", nullable = false)
    private Encargado encargado;



}
