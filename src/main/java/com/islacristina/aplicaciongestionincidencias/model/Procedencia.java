package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Procedencia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_procedencia")
    private int idProcedencia;
    @Basic
    @Column(name = "tipo_procedencia")
    private String tipoProcedencia;
    @OneToMany(mappedBy = "procedenciaByProcedenciaIncidencia")
    private Collection<Incidencia> incidenciasByIdProcedencia;

    public int getIdProcedencia() {
        return idProcedencia;
    }

    public void setIdProcedencia(int idProcedencia) {
        this.idProcedencia = idProcedencia;
    }

    public String getTipoProcedencia() {
        return tipoProcedencia;
    }

    public void setTipoProcedencia(String tipoProcedencia) {
        this.tipoProcedencia = tipoProcedencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Procedencia that = (Procedencia) o;

        if (idProcedencia != that.idProcedencia) return false;
        if (tipoProcedencia != null ? !tipoProcedencia.equals(that.tipoProcedencia) : that.tipoProcedencia != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProcedencia;
        result = 31 * result + (tipoProcedencia != null ? tipoProcedencia.hashCode() : 0);
        return result;
    }

    public Collection<Incidencia> getIncidenciasByIdProcedencia() {
        return incidenciasByIdProcedencia;
    }

    public void setIncidenciasByIdProcedencia(Collection<Incidencia> incidenciasByIdProcedencia) {
        this.incidenciasByIdProcedencia = incidenciasByIdProcedencia;
    }
}
