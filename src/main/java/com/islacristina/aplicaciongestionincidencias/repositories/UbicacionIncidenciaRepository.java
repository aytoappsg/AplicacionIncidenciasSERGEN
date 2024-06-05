package com.islacristina.aplicaciongestionincidencias.repositories;

import com.islacristina.aplicaciongestionincidencias.model.Ubicacion;
import com.islacristina.aplicaciongestionincidencias.model.UbicacionIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UbicacionIncidenciaRepository extends JpaRepository<UbicacionIncidencia, Integer> {
    List<UbicacionIncidencia> findByIncidencia_NumOrden(int numOrden);
}
