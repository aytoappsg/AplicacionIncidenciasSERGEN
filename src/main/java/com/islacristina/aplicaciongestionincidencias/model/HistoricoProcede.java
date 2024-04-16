package com.islacristina.aplicaciongestionincidencias.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "historico_procede", schema = "serviciosgenerales", catalog = "")
public class HistoricoProcede {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_historico_procede")
    private int idHistoricoProcede;
    @Basic
    @Column(name = "num_horas_estimadas")
    private double numHorasEstimadas;
    @Basic
    @Column(name = "num_trabajadores_estimados")
    private double numTrabajadoresEstimados;
    @Basic
    @Column(name = "num_horas")
    private Double numHoras;
    @Basic
    @Column(name = "num_trabajadores")
    private Double numTrabajadores;
    @Basic
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Basic
    @Column(name = "fecha_finalizado")
    private Date fechaFinalizado;
    @Basic
    @Column(name = "encargado")
    private int encargado;
    @Basic
    @Column(name = "incidencia")
    private int incidencia;
    @Basic
    @Column(name = "propuesto")
    private Byte propuesto;
    @Basic
    @Column(name = "enviado")
    private Byte enviado;
    @Basic
    @Column(name = "pendiente")
    private Byte pendiente;
    @Basic
    @Column(name = "terminado")
    private Byte terminado;
    @OneToMany(mappedBy = "historicoProcedeByHistoricoProcede")
    private Collection<HistoricoCambios> historicoCambiosByIdHistoricoProcede;
    @ManyToOne
    @JoinColumn(name = "encargado", referencedColumnName = "id_encargado", nullable = false)
    private Encargado encargadoByEncargado;
    @ManyToOne
    @JoinColumn(name = "incidencia", referencedColumnName = "num_orden", nullable = false)
    private Incidencia incidenciaByIncidencia;

    public int getIdHistoricoProcede() {
        return idHistoricoProcede;
    }

    public void setIdHistoricoProcede(int idHistoricoProcede) {
        this.idHistoricoProcede = idHistoricoProcede;
    }

    public double getNumHorasEstimadas() {
        return numHorasEstimadas;
    }

    public void setNumHorasEstimadas(double numHorasEstimadas) {
        this.numHorasEstimadas = numHorasEstimadas;
    }

    public double getNumTrabajadoresEstimados() {
        return numTrabajadoresEstimados;
    }

    public void setNumTrabajadoresEstimados(double numTrabajadoresEstimados) {
        this.numTrabajadoresEstimados = numTrabajadoresEstimados;
    }

    public Double getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(Double numHoras) {
        this.numHoras = numHoras;
    }

    public Double getNumTrabajadores() {
        return numTrabajadores;
    }

    public void setNumTrabajadores(Double numTrabajadores) {
        this.numTrabajadores = numTrabajadores;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizado() {
        return fechaFinalizado;
    }

    public void setFechaFinalizado(Date fechaFinalizado) {
        this.fechaFinalizado = fechaFinalizado;
    }

    public int getEncargado() {
        return encargado;
    }

    public void setEncargado(int encargado) {
        this.encargado = encargado;
    }

    public int getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(int incidencia) {
        this.incidencia = incidencia;
    }

    public Byte getPropuesto() {
        return propuesto;
    }

    public void setPropuesto(Byte propuesto) {
        this.propuesto = propuesto;
    }

    public Byte getEnviado() {
        return enviado;
    }

    public void setEnviado(Byte enviado) {
        this.enviado = enviado;
    }

    public Byte getPendiente() {
        return pendiente;
    }

    public void setPendiente(Byte pendiente) {
        this.pendiente = pendiente;
    }

    public Byte getTerminado() {
        return terminado;
    }

    public void setTerminado(Byte terminado) {
        this.terminado = terminado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoricoProcede that = (HistoricoProcede) o;

        if (idHistoricoProcede != that.idHistoricoProcede) return false;
        if (Double.compare(numHorasEstimadas, that.numHorasEstimadas) != 0) return false;
        if (Double.compare(numTrabajadoresEstimados, that.numTrabajadoresEstimados) != 0) return false;
        if (encargado != that.encargado) return false;
        if (incidencia != that.incidencia) return false;
        if (numHoras != null ? !numHoras.equals(that.numHoras) : that.numHoras != null) return false;
        if (numTrabajadores != null ? !numTrabajadores.equals(that.numTrabajadores) : that.numTrabajadores != null)
            return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;
        if (fechaFinalizado != null ? !fechaFinalizado.equals(that.fechaFinalizado) : that.fechaFinalizado != null)
            return false;
        if (propuesto != null ? !propuesto.equals(that.propuesto) : that.propuesto != null) return false;
        if (enviado != null ? !enviado.equals(that.enviado) : that.enviado != null) return false;
        if (pendiente != null ? !pendiente.equals(that.pendiente) : that.pendiente != null) return false;
        if (terminado != null ? !terminado.equals(that.terminado) : that.terminado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idHistoricoProcede;
        temp = Double.doubleToLongBits(numHorasEstimadas);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(numTrabajadoresEstimados);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (numHoras != null ? numHoras.hashCode() : 0);
        result = 31 * result + (numTrabajadores != null ? numTrabajadores.hashCode() : 0);
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFinalizado != null ? fechaFinalizado.hashCode() : 0);
        result = 31 * result + encargado;
        result = 31 * result + incidencia;
        result = 31 * result + (propuesto != null ? propuesto.hashCode() : 0);
        result = 31 * result + (enviado != null ? enviado.hashCode() : 0);
        result = 31 * result + (pendiente != null ? pendiente.hashCode() : 0);
        result = 31 * result + (terminado != null ? terminado.hashCode() : 0);
        return result;
    }

    public Collection<HistoricoCambios> getHistoricoCambiosByIdHistoricoProcede() {
        return historicoCambiosByIdHistoricoProcede;
    }

    public void setHistoricoCambiosByIdHistoricoProcede(Collection<HistoricoCambios> historicoCambiosByIdHistoricoProcede) {
        this.historicoCambiosByIdHistoricoProcede = historicoCambiosByIdHistoricoProcede;
    }

    public Encargado getEncargadoByEncargado() {
        return encargadoByEncargado;
    }

    public void setEncargadoByEncargado(Encargado encargadoByEncargado) {
        this.encargadoByEncargado = encargadoByEncargado;
    }

    public Incidencia getIncidenciaByIncidencia() {
        return incidenciaByIncidencia;
    }

    public void setIncidenciaByIncidencia(Incidencia incidenciaByIncidencia) {
        this.incidenciaByIncidencia = incidenciaByIncidencia;
    }
}
