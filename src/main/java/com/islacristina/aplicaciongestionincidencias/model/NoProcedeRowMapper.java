package com.islacristina.aplicaciongestionincidencias.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoProcedeRowMapper implements RowMapper<NoProcede> {

    @Override
    public NoProcede mapRow(ResultSet rs, int rowNum) throws SQLException {
        NoProcede noProcede = new NoProcede();
        // Aquí debes establecer los campos de noProcede utilizando los datos de rs
        // Por ejemplo: noProcede.setId(rs.getInt("id"));
        return noProcede;
    }
}