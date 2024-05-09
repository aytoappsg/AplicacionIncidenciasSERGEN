package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import static com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication.applicationContext;

@Controller
public class MainController implements Initializable {

    @FXML
    private StackPane stackPane;

    public void loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(AplicacionGestionIncidenciasApplication.class.getResource(fxmlFile));
            loader.setControllerFactory(applicationContext::getBean);
            Parent root = loader.load();
            addStackPaneChildren(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFXML("/login.fxml");
    }

    public void addStackPaneChildren(Parent root){
        stackPane.getChildren().clear();
        stackPane.getChildren().add(root);
    }

    public void setMainScene(Parent scene) {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(scene);
    }
}
