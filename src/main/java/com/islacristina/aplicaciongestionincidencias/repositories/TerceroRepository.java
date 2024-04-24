package com.islacristina.aplicaciongestionincidencias.repositories;

import com.islacristina.aplicaciongestionincidencias.model.Tercero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerceroRepository extends JpaRepository<Tercero, Integer> {
    Tercero findByDniCif(String dniCif);

    Tercero findByEmail(String email);
}
