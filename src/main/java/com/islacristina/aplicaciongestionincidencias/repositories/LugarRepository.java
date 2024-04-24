package com.islacristina.aplicaciongestionincidencias.repositories;

import com.islacristina.aplicaciongestionincidencias.model.Lugar;
import com.islacristina.aplicaciongestionincidencias.model.Procedencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Integer> {
}
