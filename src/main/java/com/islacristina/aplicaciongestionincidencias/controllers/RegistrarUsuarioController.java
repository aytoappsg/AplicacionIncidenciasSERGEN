package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import com.islacristina.aplicaciongestionincidencias.enums.Rol; // Asegúrate de importar tu Enum de roles
import com.islacristina.aplicaciongestionincidencias.repositories.UsuarioRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

@Controller
public class RegistrarUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private ComboBox<String> roleCombobox;

    @FXML
    public void initialize() {
        // Obtén los roles de tu Enum y filtra el rol de SuperAdministrador
        List<String> roles = Arrays.stream(Rol.values())
                .map(Enum::name)
                .filter(role -> !role.equals("SuperAdministrador"))
                .collect(Collectors.toList());

        // Agrega los roles al ComboBox
        roleCombobox.getItems().addAll(roles);

        // Establece el texto predeterminado
        roleCombobox.setPromptText("Seleccione un rol...");

        registerButton.setOnAction(event -> {
            String name = nameField.getText();
            String password = passwordField.getText();
            String role = roleCombobox.getValue(); // Obtener el valor seleccionado del ComboBox

            // Verifica si el rol ingresado está en la lista de roles predeterminados
            if (!roles.contains(role)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("El rol ingresado no es uno de los roles predeterminados");
                alert.setContentText("¿Estás seguro de que quieres continuar?");

                if (alert.showAndWait().get() != ButtonType.OK) {
                    // Si el usuario no confirma, borra el rol ingresado y termina el evento
                    roleCombobox.getSelectionModel().clearSelection();
                    return;
                }
            }

            if (usuarioRepository.findByName(name) != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("El nombre de usuario ya existe");
                alert.showAndWait();
            } else if (password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("La contraseña no puede estar vacía");
                alert.showAndWait();
            } else if (role == null || role.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("El rol no puede estar vacío");
                alert.showAndWait();

            } else {
                Usuario usuario = new Usuario();
                usuario.setName(name);
                usuario.setContrasena(password);
                usuario.setRole(role);
                usuarioRepository.save(usuario);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Usuario registrado con éxito");
                alert.showAndWait();
            }
        });
    }
}