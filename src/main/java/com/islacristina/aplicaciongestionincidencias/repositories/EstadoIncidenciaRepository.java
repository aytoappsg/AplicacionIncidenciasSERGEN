package com.islacristina.aplicaciongestionincidencias.repositories;

import com.islacristina.aplicaciongestionincidencias.model.EstadoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoIncidenciaRepository extends JpaRepository<EstadoIncidencia, Long> {
    EstadoIncidencia findByNombre(String nombre);
}