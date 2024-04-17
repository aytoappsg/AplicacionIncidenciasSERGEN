package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.exceptions.InvalidCredentialsException;
import com.islacristina.aplicaciongestionincidencias.exceptions.UserNotFoundException;
import com.islacristina.aplicaciongestionincidencias.model.User;
import com.islacristina.aplicaciongestionincidencias.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarSesionButton.setOnAction(event -> iniciarSesion());

        contrasenhaField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                iniciarSesion();
            }
        });
    }

    @FXML
    private void iniciarSesion() {
        try {
            String usuario = usuarioField.getText();
            String contrasena = contrasenhaField.getText();

            // Verificar si los campos de usuario y contraseña están vacíos
            if (usuario == null || usuario.isEmpty()) {
                showAlert("Error de inicio de sesión", "El campo de usuario está vacío");
                return;
            }

            if (contrasena == null || contrasena.isEmpty()) {
                showAlert("Error de inicio de sesión", "El campo de contraseña está vacío");
                return;
            }

            User user = userService.findByName(usuario);
            if (user == null) {
                showAlert("Error de inicio de sesión", "El usuario no existe");
                return;
            }

            if (!user.getContrasena().equals(contrasena)) {
                showAlert("Error de inicio de sesión", "Contraseña incorrecta");
                return;
            }

            System.out.println("Usuario autenticado: ");
        } catch (Exception e) {
            showAlert("Error", "Ocurrió un error desconocido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}