package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.exceptions.InvalidCredentialsException;
import com.islacristina.aplicaciongestionincidencias.exceptions.UserNotFoundException;
import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import com.islacristina.aplicaciongestionincidencias.services.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import static com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication.applicationContext;

/**
 * Controlador para la ventana de inicio de sesión.
 */
@Controller
public class LoginController implements Initializable {

    @Autowired
    private MainController mainController;

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField contrasenhaField;

    @FXML
    private Button iniciarSesionButton;

    private final UsuarioService usuarioService;

    /**
     * Constructor para inyectar el servicio de usuario.
     *
     * @param userService El servicio de usuario.
     */
    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Inicializa el controlador después de que se haya cargado su elemento raíz.
     *
     * @param url            La ubicación para resolver rutas relativas de recursos.
     * @param resourceBundle Los recursos localizados.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar el evento del botón de inicio de sesión
        iniciarSesionButton.setOnAction(event -> iniciarSesion());

        // Configurar el evento para iniciar sesión cuando se presiona la tecla Enter en el campo de contraseña
        contrasenhaField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                iniciarSesion();
            }
        });
    }

    /**
     * Método para iniciar sesión.
     */
    @FXML
    private void iniciarSesion() {
        try {
            String usuario = usuarioField.getText();
            String contrasena = contrasenhaField.getText();

            // Validar campos de usuario y contraseña
            if (usuario == null || usuario.isEmpty()) {
                showAlert("Error de inicio de sesión", "El campo de usuario está vacío");
                return;
            }

            if (contrasena == null || contrasena.isEmpty()) {
                showAlert("Error de inicio de sesión", "El campo de contraseña está vacío");
                return;
            }

            User user = userService.login(usuario, contrasena);

            // Cargar el dashboard si el inicio de sesión fue exitoso
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            Parent root = loader.load();
            mainController.addStackPaneChildren(root);

            DashboardController dashboardController = loader.getController();
            dashboardController.setUser(user);
        } catch (UserNotFoundException e) {
            showAlert("Error de inicio de sesión", "El usuario no existe");
        } catch (InvalidCredentialsException e) {
            showAlert("Error de inicio de sesión", "Contraseña incorrecta");
        } catch (Exception e) {
            // Capturar excepciones generales y mostrarlas
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método para mostrar un diálogo de alerta.
     *
     * @param title   El título de la alerta.
     * @param message El mensaje de la alerta.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
