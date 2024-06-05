package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.enums.Rol;
import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import com.islacristina.aplicaciongestionincidencias.repositories.UsuarioRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para la ventana de registro de usuarios.
 */
@Controller
public class RegistrarUsuarioController {

    @Autowired
    private UsuarioRepository userRepository;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private ComboBox<String> roleCombobox;

    /**
     * Inicializa el controlador después de que se haya cargado su elemento raíz.
     */
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

        // Configura el evento de clic del botón de registro
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

            // Validaciones de nombre de usuario, contraseña y rol
            if (userRepository.findByName(name) != null) {
                showAlert(Alert.AlertType.WARNING, "Warning Dialog", "El nombre de usuario ya existe");
            } else if (password.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning Dialog", "La contraseña no puede estar vacía");
            } else if (role == null || role.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning Dialog", "El rol no puede estar vacío");
            } else {
                // Crear y guardar el nuevo usuario
                Usuario user = new Usuario();
                user.setName(name);
                user.setContrasena(password);
                user.setRole(role);
                userRepository.save(user);

                showAlert(Alert.AlertType.INFORMATION, "Information Dialog", "Usuario registrado con éxito");
            }
        });
    }

    /**
     * Muestra un diálogo de alerta con el tipo, título y mensaje especificados.
     *
     * @param type    El tipo de alerta.
     * @param title   El título del diálogo.
     * @param message El mensaje a mostrar.
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
