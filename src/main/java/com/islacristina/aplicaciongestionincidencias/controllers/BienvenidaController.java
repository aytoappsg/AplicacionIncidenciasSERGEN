package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class BienvenidaController {

    @FXML
    private Label welcomeLabel;
    private User user;

    public void setUsuario(User usuario) {
        user = usuario;
    }

    public void mostrarInformacionUsuario() {
        if (user != null) {
            String nombreUsuario = user.getName();
            String rolUsuario = user.getRole();
            welcomeLabel.setText("Bienvenido " + nombreUsuario + ", estás usando el rol " + rolUsuario);
        } else {
            welcomeLabel.setText("No se ha proporcionado información de usuario");
        }
    }
}