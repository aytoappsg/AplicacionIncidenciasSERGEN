package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class BienvenidaController implements Initializable {

    @FXML
    private Label welcomeLabel;

    private Usuario usuario;

    public void mostrarInformacionUsuario() {
       if (usuario != null) {
            String nombreUsuario = usuario.getName();
            String rolUsuario = usuario.getRole();
           System.out.println("Usuario: " + usuario.getName());
           System.out.println("Rol: " + usuario.getRole());
            welcomeLabel.setText("Bienvenido " + nombreUsuario + ", estás usando el rol " + rolUsuario);
      } else {
            welcomeLabel.setText("No se ha proporcionado información de usuario");
        }
    }

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
        mostrarInformacionUsuario();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}