package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tipo_de_lugar")
public class TipoUbicacion {
    @Id
    @Column(name = "id_tipo_lugar")
    private int idTipoLugar;

    @Column(name = "tipo_lugar")
    private String tipoProcedencia;

}
