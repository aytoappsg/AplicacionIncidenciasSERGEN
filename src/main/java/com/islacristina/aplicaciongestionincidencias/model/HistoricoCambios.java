package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "historico_cambios", schema = "serviciosgenerales", catalog = "")
public class HistoricoCambios {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_historico_cambios")
    private int idHistoricoCambios;
    @Basic
    @Column(name = "usuario")
    private int usuario;
    @Basic
    @Column(name = "historico_procede")
    private int historicoProcede;
    @Basic
    @Column(name = "estado_anterior")
    private String estadoAnterior;
    @Basic
    @Column(name = "nuevo_estado")
    private String nuevoEstado;
    @Basic
    @Column(name = "fecha_cambio")
    private Timestamp fechaCambio;
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioByUsuario;
    @ManyToOne
    @JoinColumn(name = "historico_procede", referencedColumnName = "id_historico_procede", nullable = false)
    private HistoricoProcede historicoProcedeByHistoricoProcede;

    public int getIdHistoricoCambios() {
        return idHistoricoCambios;
    }

    public void setIdHistoricoCambios(int idHistoricoCambios) {
        this.idHistoricoCambios = idHistoricoCambios;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getHistoricoProcede() {
        return historicoProcede;
    }

    public void setHistoricoProcede(int historicoProcede) {
        this.historicoProcede = historicoProcede;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getNuevoEstado() {
        return nuevoEstado;
    }

    public void setNuevoEstado(String nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }

    public Timestamp getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Timestamp fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoricoCambios that = (HistoricoCambios) o;

        if (idHistoricoCambios != that.idHistoricoCambios) return false;
        if (usuario != that.usuario) return false;
        if (historicoProcede != that.historicoProcede) return false;
        if (estadoAnterior != null ? !estadoAnterior.equals(that.estadoAnterior) : that.estadoAnterior != null)
            return false;
        if (nuevoEstado != null ? !nuevoEstado.equals(that.nuevoEstado) : that.nuevoEstado != null) return false;
        if (fechaCambio != null ? !fechaCambio.equals(that.fechaCambio) : that.fechaCambio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHistoricoCambios;
        result = 31 * result + usuario;
        result = 31 * result + historicoProcede;
        result = 31 * result + (estadoAnterior != null ? estadoAnterior.hashCode() : 0);
        result = 31 * result + (nuevoEstado != null ? nuevoEstado.hashCode() : 0);
        result = 31 * result + (fechaCambio != null ? fechaCambio.hashCode() : 0);
        return result;
    }

    public Usuario getUsuarioByUsuario() {
        return usuarioByUsuario;
    }

    public void setUsuarioByUsuario(Usuario usuarioByUsuario) {
        this.usuarioByUsuario = usuarioByUsuario;
    }

    public HistoricoProcede getHistoricoProcedeByHistoricoProcede() {
        return historicoProcedeByHistoricoProcede;
    }

    public void setHistoricoProcedeByHistoricoProcede(HistoricoProcede historicoProcedeByHistoricoProcede) {
        this.historicoProcedeByHistoricoProcede = historicoProcedeByHistoricoProcede;
    }
}
