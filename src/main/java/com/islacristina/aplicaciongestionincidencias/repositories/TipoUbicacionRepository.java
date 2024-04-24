package com.islacristina.aplicaciongestionincidencias.repositories;

import com.islacristina.aplicaciongestionincidencias.model.TipoUbicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUbicacionRepository extends JpaRepository<TipoUbicacion, Integer> {
}
