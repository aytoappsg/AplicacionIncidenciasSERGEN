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
@Table(name = "grupo_trabajo")
public class GrupoTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo_trabajo")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "grupoTrabajo")
    private List<Encargado> encargados;



}
