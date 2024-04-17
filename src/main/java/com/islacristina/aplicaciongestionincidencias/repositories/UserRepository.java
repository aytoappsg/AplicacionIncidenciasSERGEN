package com.islacristina.aplicaciongestionincidencias.repositories;

import com.islacristina.aplicaciongestionincidencias.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    //Metodo que nos de usando springboot el id del usuario que se meta por parametro el nombre y atraves del nombre el id
    //Atraves del id que nos devuelva un usuario (todo con springboot)
    User findByName(String name);
}
