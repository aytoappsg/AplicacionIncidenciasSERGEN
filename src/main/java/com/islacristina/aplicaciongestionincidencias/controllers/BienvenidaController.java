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

    public void setUsuario(User usuario) {
        user = usuario;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}