package com.islacristina.aplicaciongestionincidencias.services;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import com.islacristina.aplicaciongestionincidencias.model.IncidenciaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class NoProcedeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Incidencia getLastNoProcede() {
        String sql = "SELECT * FROM incidencia WHERE estado_incidencia = 2 ORDER BY num_orden DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new IncidenciaRowMapper());
    }
}