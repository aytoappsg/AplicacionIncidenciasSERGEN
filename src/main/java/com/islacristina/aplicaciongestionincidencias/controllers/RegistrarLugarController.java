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

@Controller
public class RegistrarLugarController implements Initializable {

    @FXML
    private TextField placeNameField;

    @Autowired
    private LugarService lugarService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void savePlace() {
        String placeName = placeNameField.getText();

        if (placeName.isEmpty()) {
            // Mostrar un mensaje de error
            return;
        }

        Lugar newPlace = new Lugar();
        newPlace.setNombreLugar(placeName);

        lugarService.saveLugar(newPlace);

        // Limpiar el campo de texto
        placeNameField.clear();
    }
}