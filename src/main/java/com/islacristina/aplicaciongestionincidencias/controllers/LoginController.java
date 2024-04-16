package com.islacristina.aplicaciongestionincidencias.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
@Component
public class LoginController implements Initializable {

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField contrasenhaField;

    @FXML
    private Button iniciarSesionButton;

    // Método llamado al inicializar el controlador
    @FXML
    public void initialize() {
        // Puedes agregar aquí la inicialización de componentes adicionales, si es necesario
    }

    // Método llamado al hacer clic en el botón de iniciar sesión
    @FXML
    private void iniciarSesion() {
        String usuario = usuarioField.getText();
        String contraseña = contrasenhaField.getText();
        // Aquí puedes implementar la lógica para iniciar sesión utilizando los datos ingresados
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}