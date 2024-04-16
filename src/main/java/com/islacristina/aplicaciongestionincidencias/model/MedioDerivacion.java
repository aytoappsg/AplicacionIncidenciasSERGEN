package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "medio_derivacion", schema = "serviciosgenerales", catalog = "")
public class MedioDerivacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_medio_derivacion")
    private int idMedioDerivacion;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "medioDerivacionByMedioDerivacion")
    private Collection<HistoricoDerivada> historicoDerivadasByIdMedioDerivacion;

    public int getIdMedioDerivacion() {
        return idMedioDerivacion;
    }

    public void setIdMedioDerivacion(int idMedioDerivacion) {
        this.idMedioDerivacion = idMedioDerivacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedioDerivacion that = (MedioDerivacion) o;

        if (idMedioDerivacion != that.idMedioDerivacion) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMedioDerivacion;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public Collection<HistoricoDerivada> getHistoricoDerivadasByIdMedioDerivacion() {
        return historicoDerivadasByIdMedioDerivacion;
    }

    public void setHistoricoDerivadasByIdMedioDerivacion(Collection<HistoricoDerivada> historicoDerivadasByIdMedioDerivacion) {
        this.historicoDerivadasByIdMedioDerivacion = historicoDerivadasByIdMedioDerivacion;
    }
}
