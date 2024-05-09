package com.islacristina.aplicaciongestionincidencias.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncidenciaRowMapper implements RowMapper<Incidencia> {

    @Override
    public Incidencia mapRow(ResultSet rs, int rowNum) throws SQLException {
        Incidencia incidencia = new Incidencia();
        incidencia.setNumOrden(rs.getInt("num_orden"));
        incidencia.setEstadoIncidencia(rs.getInt("estado_incidencia"));
        // Aquí debes establecer los demás campos de incidencia utilizando los datos de rs
        // Por ejemplo: incidencia.setOtroCampo(rs.getTipo("nombre_columna"));
        return incidencia;
    }
}