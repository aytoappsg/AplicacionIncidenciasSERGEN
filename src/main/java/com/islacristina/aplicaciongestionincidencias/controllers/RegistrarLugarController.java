package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Lugar;
import com.islacristina.aplicaciongestionincidencias.services.LugarService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana de registro de lugares.
 */
@Controller
public class RegistrarLugarController implements Initializable {

    @FXML
    private TextField placeNameField;

    @Autowired
    private LugarService lugarService;

    /**
     * Inicializa el controlador después de que se haya cargado su elemento raíz.
     *
     * @param url            La ubicación para resolver rutas relativas de recursos.
     * @param resourceBundle Los recursos localizados.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Maneja el evento de guardar un lugar.
     */
    @FXML
    private void savePlace() {
        String placeName = placeNameField.getText();

        // Verifica si el nombre del lugar está vacío
        if (placeName.isEmpty()) {
            // Mostrar un mensaje de error
            // Aquí podrías mostrar una alerta para indicar al usuario que el nombre del lugar es obligatorio.
            return;
        }

        // Crea un nuevo objeto Lugar con el nombre proporcionado
        Lugar newPlace = new Lugar();
        newPlace.setNombreLugar(placeName);

        // Guarda el nuevo lugar usando el servicio de lugar
        lugarService.saveLugar(newPlace);

        // Limpiar el campo de texto después de guardar el lugar
        placeNameField.clear();
    }
}
