package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
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

    //Atributos relacionados con los elementos de la vista
    @FXML
    private StackPane stackPane;

    /**
     * Método que inicializa la vista principal de la aplicación, por defecto
     * carga la vista de login.
     *
     * @param url: ?
     * @param resourceBundle: ?
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFXML("/login.fxml");
    }

    /**
     * Método para cargar un archivo FXML y llamar al método que carga el nodo en el
     * stack pane principal.
     *
     * @param fxmlFile Nombre del archivo FXML a cargar.
     */
    public void loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(AplicacionGestionIncidenciasApplication.class.getResource(fxmlFile));
            loader.setControllerFactory(applicationContext::getBean);
            Parent root = loader.load();
            addStackPaneChildren(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método para añadir un nodo hijo al StackPane principal.
     *
     * @param root Nodo hijo a añadir.
     */
    public void addStackPaneChildren(Parent root) {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(root);
    }
}
