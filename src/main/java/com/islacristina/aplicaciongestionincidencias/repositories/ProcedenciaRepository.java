package com.islacristina.aplicaciongestionincidencias.repositories;


import com.islacristina.aplicaciongestionincidencias.model.Procedencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedenciaRepository extends JpaRepository<Procedencia, Integer> {

}
