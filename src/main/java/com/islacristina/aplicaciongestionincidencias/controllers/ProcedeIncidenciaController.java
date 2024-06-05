package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication.applicationContext;

@Controller
public class ProcedeIncidenciaController implements Initializable {

    @FXML
    private VBox vboxAsignar;

    @FXML
    private Button btnAdd;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void addItem() throws IOException {
        FXMLLoader loader = new FXMLLoader(AplicacionGestionIncidenciasApplication.class.getResource("/asignar_item.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Parent root = loader.load();
        vboxAsignar.getChildren().add(root);
    }
}