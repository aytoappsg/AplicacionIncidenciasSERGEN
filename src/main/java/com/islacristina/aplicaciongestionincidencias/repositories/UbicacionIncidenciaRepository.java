package com.islacristina.aplicaciongestionincidencias.repositories;

import com.islacristina.aplicaciongestionincidencias.model.Ubicacion;
import com.islacristina.aplicaciongestionincidencias.model.UbicacionIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionIncidenciaRepository extends JpaRepository<UbicacionIncidencia, Integer> {
}
