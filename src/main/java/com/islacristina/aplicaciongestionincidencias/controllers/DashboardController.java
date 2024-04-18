package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DashboardController implements Initializable {

    @FXML
    private BorderPane rootPane;

    @FXML
    private HBox topHBox;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    private User usuario;

    @FXML
    private void initialize() {
        // Agregar acciones a los botones
        btn1.setOnAction(this::cambiarContenido);
        btn2.setOnAction(this::cambiarContenido);
        btn3.setOnAction(this::cambiarContenido);
    }

    @FXML
    private void cambiarContenido(ActionEvent event) {
        // Lógica para cambiar el contenido central según el botón presionado
        // Puedes usar un switch o if-else para determinar qué contenido cargar
        // Por ejemplo:
        if (event.getSource() instanceof Button) {
            Button botonPresionado = (Button) event.getSource();
            String idContenido = botonPresionado.getId();
            String fxmlPath = "";

            switch (idContenido) {
                case "btn1":
                    fxmlPath = "/insertarIncidencia.fxml";
                    break;
                case "btn2":
                    fxmlPath = "/verIncidencias.fxml";
                    break;
                case "btn3":
                    fxmlPath = "/estadisticas.fxml";
                    break;
                case "btn4":
                    fxmlPath = "/administrador.fxml";
                    break;
                default:
                    // En caso de no reconocer el botón, puedes manejarlo aquí
            }

            if (!fxmlPath.isEmpty()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                    Node contenido = loader.load();
                    rootPane.setCenter(contenido);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void cargarPantallaBienvenida() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bienvenida.fxml"));
            Node contenido = loader.load();

            // Pasa la información del usuario a la pantalla de bienvenida si está disponible
            BienvenidaController controller = loader.getController();
            controller.setUsuario(usuario);
            controller.mostrarInformacionUsuario();

            rootPane.setCenter(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarPantallaBienvenida();
    }

    public void mostrarPantallaBienvenida() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bienvenida.fxml"));
            Node contenido = loader.load();

            // Pasa la información del usuario a la pantalla de bienvenida si está disponible
            BienvenidaController controller = loader.getController();
            controller.setUsuario(usuario);
            controller.mostrarInformacionUsuario();

            rootPane.setCenter(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}