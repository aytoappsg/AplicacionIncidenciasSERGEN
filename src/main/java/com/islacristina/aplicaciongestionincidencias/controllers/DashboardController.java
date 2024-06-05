package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication.applicationContext;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Controller
public class DashboardController implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private Usuario usuario;

    @FXML
    private Incidencia incidencia;

    @Autowired
    @Lazy
    private VerIncidenciasController verIncidenciasController; // Añadir esta línea


    /**
     * Método que inicializa la vista principal de la aplicación, por defecto
     * carga la vista de bienvenida.
     *
     * @param url: ?
     * @param resourceBundle: ?
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBienvenida();
    }

    /**
     * Método para cargar la vista de bienvenida en el StackPane principal.
     */
    private void loadBienvenida() {
        try {
            FXMLLoader bienvenidaLoader = new FXMLLoader(getClass().getResource("/bienvenida.fxml"));
            bienvenidaLoader.setControllerFactory(applicationContext::getBean);
            AnchorPane dashboardPane = bienvenidaLoader.load();

            BienvenidaController bienvenidaController = bienvenidaLoader.getController();
            bienvenidaController.setUsuario(usuario);

            stackPane.getChildren().clear(); // Limpiar los hijos existentes del StackPane
            stackPane.getChildren().add(dashboardPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para cargar la vista de insertar incidencia en el StackPane principal.
     */
    @FXML
    private void loadInsertar() {
        try {
            FXMLLoader insertarLoader = new FXMLLoader(getClass().getResource("/insertar_incidencia.fxml"));
            insertarLoader.setControllerFactory(applicationContext::getBean);
            Parent insertarPane = insertarLoader.load();

            InsertarIncidenciaController insertarIncidenciaController = insertarLoader.getController();
            insertarIncidenciaController.setUsuarioActual(usuario);

            stackPane.getChildren().clear(); // Limpiar los hijos existentes del StackPane
            stackPane.getChildren().add(insertarPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para cargar la vista de ver incidencias en el StackPane principal.
     */
    @FXML
    private void loadVerIncidencias(){
        try {
            FXMLLoader verIncidenciasLoader = new FXMLLoader(getClass().getResource("/ver_incidencias.fxml"));
            verIncidenciasLoader.setControllerFactory(applicationContext::getBean);
            Parent verIncidenciasPane = verIncidenciasLoader.load();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(verIncidenciasPane);

            VerIncidenciasController verIncidenciasController = verIncidenciasLoader.getController();
            verIncidenciasController.setDashboardController(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void loadResumen(Incidencia incidenciaSeleccionada){
        try {

            FXMLLoader detallesLoader = new FXMLLoader(getClass().getResource("/resumen_incidencia.fxml"));
            detallesLoader.setControllerFactory(applicationContext::getBean);
            Parent detallesPane = detallesLoader.load();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(detallesPane);
            System.out.println("Incidencia seleccionada1: " + incidenciaSeleccionada.getNumOrden());

            ResumenIncidenciaController resumenIncidenciaController = detallesLoader.getController();
            resumenIncidenciaController.setDashboardController(this);
            resumenIncidenciaController.setIncidencia(incidenciaSeleccionada);
            System.out.println("Incidencia seleccionada2: " + incidenciaSeleccionada.getNumOrden());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para cargar la vista de administrador en el StackPane principal.
     */
    @FXML
    private void loadAdministrador(){
        try {
            FXMLLoader verIncidenciasLoader = new FXMLLoader(getClass().getResource("/admin_options.fxml"));
            verIncidenciasLoader.setControllerFactory(applicationContext::getBean);
            Parent verIncidenciasPane = verIncidenciasLoader.load();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(verIncidenciasPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMainContent(Parent root) {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(root);
    }

}