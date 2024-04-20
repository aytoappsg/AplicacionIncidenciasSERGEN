package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class BienvenidaController implements Initializable {

    @FXML
    private Label welcomeLabel;
    private User user;

    public void mostrarInformacionUsuario() {
        if (user != null) {
            String nombreUsuario = user.getName();
            String rolUsuario = user.getRole();
            welcomeLabel.setText("Bienvenido " + nombreUsuario + ", estás usando el rol " + rolUsuario);
        } else {
            welcomeLabel.setText("No se ha proporcionado información de usuario");
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarInformacionUsuario();
    }
}