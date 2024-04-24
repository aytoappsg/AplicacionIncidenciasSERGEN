package com.islacristina.aplicaciongestionincidencias.repositories;


import com.islacristina.aplicaciongestionincidencias.model.Procedencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedenciaRepository extends JpaRepository<Procedencia, Long> {
    Procedencia findByTipoProcedencia(String tipoProcedencia);
}