package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import org.springframework.stereotype.Controller;

@Controller
public class ModificarUsuariosController {

    private Usuario usuario;
    private boolean modified = false;

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isModified() {
        return modified;
    }
}
