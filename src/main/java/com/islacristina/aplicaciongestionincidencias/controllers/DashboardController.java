package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication;
import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
    private Usuario usuario;

    public DashboardController(){}

    @FXML
    private void cambiarContenido() {
        loadFXML("/insertar_incidencia.fxml");
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

    }

    private void loadBienvenida() {

        try {
            FXMLLoader bienvenidaLoader = new FXMLLoader(getClass().getResource("/bienvenida.fxml"));
            bienvenidaLoader.setControllerFactory(applicationContext::getBean);
            AnchorPane dashboardPane = bienvenidaLoader.load();

            BienvenidaController bienvenidaController = bienvenidaLoader.getController();
            bienvenidaController.setUser(usuario);

            stackPane.getChildren().clear(); // Limpiar los hijos existentes del StackPane
            stackPane.getChildren().add(dashboardPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
        if (stackPane != null) {
            loadBienvenida();
        }
    }
}