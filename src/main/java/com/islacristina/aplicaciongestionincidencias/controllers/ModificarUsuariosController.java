package com.islacristina.aplicaciongestionincidencias.controllers;


import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import org.springframework.stereotype.Controller;

@Controller
public class ModificarUsuariosController {

    private Usuario user;
    private boolean modified = false;

    public void setUser(Usuario user) {
        this.user = user;
    }

    public boolean isModified() {
        return modified;
    }
}
