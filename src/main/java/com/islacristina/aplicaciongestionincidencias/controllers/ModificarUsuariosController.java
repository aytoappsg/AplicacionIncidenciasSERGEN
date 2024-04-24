package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.User;
import org.springframework.stereotype.Controller;

@Controller
public class ModificarUsuariosController {

    private User user;
    private boolean modified = false;

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isModified() {
        return modified;
    }
}
