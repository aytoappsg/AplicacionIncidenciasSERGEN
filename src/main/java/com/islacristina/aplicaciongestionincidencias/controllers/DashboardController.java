package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication;
import com.islacristina.aplicaciongestionincidencias.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication.applicationContext;

@Controller
public class DashboardController implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private User user;

    public DashboardController(User user) {
        this.user = user;
    }

    public DashboardController(){}

    @FXML
    private void cambiarContenido() {
        loadFXML("/adminOptions.fxml");
    }

    private void loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(AplicacionGestionIncidenciasApplication.class.getResource(fxmlFile));
            loader.setControllerFactory(applicationContext::getBean);
            Parent root = loader.load();
            stackPane.getChildren().clear(); // Limpiar los hijos existentes del StackPane
            stackPane.getChildren().add(root); // Agregar el nuevo contenido
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader bienvenidaLoader = new FXMLLoader(getClass().getResource("/bienvenida.fxml"));
        bienvenidaLoader.setControllerFactory(applicationContext::getBean);
        try {
            AnchorPane dashboardPane = bienvenidaLoader.load();
            stackPane.getChildren().clear(); // Limpiar los hijos existentes del StackPane
            stackPane.getChildren().add(dashboardPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BienvenidaController bienvenidaController = bienvenidaLoader.getController();

        // Configurar el usuario en el controlador del dashboard
        bienvenidaController.setUser(user);
    }

    public void setUser(User user) {
        this.user = user;
    }
}