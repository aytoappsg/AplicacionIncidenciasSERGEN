package com.islacristina.aplicaciongestionincidencias.repositories;

import com.islacristina.aplicaciongestionincidencias.model.EstadoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoIncidenciaRepository extends JpaRepository<EstadoIncidencia, Integer> {
    EstadoIncidencia findByNombre(String nombre);
}