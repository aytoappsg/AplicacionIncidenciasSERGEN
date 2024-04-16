package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Destinatario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_destinatario")
    private int idDestinatario;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "destinatarioByIdDestinatario")
    private Collection<HistoricoDerivada> historicoDerivadasByIdDestinatario;

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
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

        Destinatario that = (Destinatario) o;

        if (idDestinatario != that.idDestinatario) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDestinatario;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public Collection<HistoricoDerivada> getHistoricoDerivadasByIdDestinatario() {
        return historicoDerivadasByIdDestinatario;
    }

    public void setHistoricoDerivadasByIdDestinatario(Collection<HistoricoDerivada> historicoDerivadasByIdDestinatario) {
        this.historicoDerivadasByIdDestinatario = historicoDerivadasByIdDestinatario;
    }
}
