package com.islacristina.aplicaciongestionincidencias.repo;

import com.islacristina.aplicaciongestionincidencias.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {

}
