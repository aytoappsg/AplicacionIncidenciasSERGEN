package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication;
import com.islacristina.aplicaciongestionincidencias.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private Button btnInicio;

    @FXML
    private Button btnInsertarIncidenciaButton;

    @FXML
    private Button verIncidenciasButton;

    @FXML
    private Button estadisticasButton;

    @FXML
    private Button opcionesAdminButton;

    @FXML
    private User user;

    public DashboardController(){}

    @FXML
    private void handleInsertarIncidenciaButtonAction() {
        loadFXML("/insertar_incidencia.fxml");
    }
    @FXML
    private void handleVerIncidenciasButtonAction() {
        loadFXML("/verIncidencias.fxml");
    }

    @FXML
    private void handleEstadisticasButtonAction() {
        //loadFXML("/estadisticas.fxml"); TODAVIA NO SE HA ACTIVADO ESTA VISTA, POR ESO ESTÁ COMENTADA.
    }

    @FXML
    private void handleOpcionesAdminButtonAction() {
        loadFXML("/adminOptions.fxml");
    }

    @FXML
    private void cambiarContenido() {
        loadFXML("/bienvenida.fxml");
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
        loadBienvenida();
        btnInicio.getStyleClass().clear();
        btnInicio.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        btnInicio.setTextFill(Color.web("#748CF1"));
    }

    private void loadBienvenida() {

        try {
            FXMLLoader bienvenidaLoader = new FXMLLoader(getClass().getResource("/bienvenida.fxml"));
            bienvenidaLoader.setControllerFactory(applicationContext::getBean);
            AnchorPane dashboardPane = bienvenidaLoader.load();


            BienvenidaController bienvenidaController = bienvenidaLoader.getController();
            bienvenidaController.setUser(user);

            stackPane.getChildren().clear(); // Limpiar los hijos existentes del StackPane
            stackPane.getChildren().add(dashboardPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUser(User user) {
        this.user = user;
        if (stackPane != null) {
            loadBienvenida();
        }
    }

    public void setMainContent(Parent content) {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(content);
    }
}