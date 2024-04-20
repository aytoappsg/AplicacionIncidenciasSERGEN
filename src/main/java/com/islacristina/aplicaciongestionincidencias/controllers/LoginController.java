package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.exceptions.InvalidCredentialsException;
import com.islacristina.aplicaciongestionincidencias.exceptions.UserNotFoundException;
import com.islacristina.aplicaciongestionincidencias.model.User;
import com.islacristina.aplicaciongestionincidencias.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

@Controller
public class LoginController implements Initializable {

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField contrasenhaField;

    @FXML
    private Button iniciarSesionButton;

    private Stage primaryStage;

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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void iniciarSesion() {
        try {
            String usuario = usuarioField.getText();
            String contrasena = contrasenhaField.getText();

            if (usuario == null || usuario.isEmpty()) {
                showAlert("Error de inicio de sesión", "El campo de usuario está vacío");
                return;
            }

            if (contrasena == null || contrasena.isEmpty()) {
                showAlert("Error de inicio de sesión", "El campo de contraseña está vacío");
                return;
            }

            // Realizar la autenticación
            User user = userService.login(usuario, contrasena);

            // Cargar el dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            AnchorPane dashboardPane = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.setUser(user);

            Scene scene = new Scene(dashboardPane);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();

        } catch (UserNotFoundException e) {
            showAlert("Error de inicio de sesión", "El usuario no existe");
        } catch (InvalidCredentialsException e) {
            showAlert("Error de inicio de sesión", "Contraseña incorrecta");
        } catch (Exception e) {
            System.out.println( (e.getMessage()));
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