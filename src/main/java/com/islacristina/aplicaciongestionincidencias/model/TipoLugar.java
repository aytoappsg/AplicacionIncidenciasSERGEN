package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tipo_de_lugar")
public class TipoLugar {
    @Id
    @Column(name = "id_tipo_lugar")
    private int idTipoLugar;

    @Column(name = "tipo_lugar")
    private String tipoProcedencia;

    @OneToMany(mappedBy = "lugar")
    private List<Ubicacion> ubicaciones;

}
