package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Controller
public class BienvenidaController implements Initializable {

    //Atributos relacionados con los elementos de la vista
    @FXML
    private Label welcomeLabel;

    private Usuario usuario;

    //Método que se ejecuta al cargar la vista
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarInformacionUsuario();
    }

    /**
     * Método para mostrar la información del usuario en la vista.
     */
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
}