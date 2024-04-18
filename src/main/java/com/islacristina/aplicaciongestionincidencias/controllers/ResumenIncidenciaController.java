package com.islacristina.aplicaciongestionincidencias.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ResumenIncidenciaController {

    @FXML
    private ComboBox<String> ComboBoxEstado;

    @FXML
    private Label LabelMotivo;

    @FXML
    private TextArea TextAreaMotivo;

    @FXML
    public void initialize() {
        // Agrega un listener al ComboBox
        ComboBoxEstado.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            // Si el nuevo valor es "Pendiente", muestra el Label y el TextArea y actualiza el texto del Label
            if ("Pendiente".equals(newValue)) {
                LabelMotivo.setText("Motivo Pendiente:");
                TextAreaMotivo.setVisible(true);
                LabelMotivo.setVisible(true);
            } else {
                // Si el nuevo valor no es "Pendiente", oculta el Label y el TextArea
                TextAreaMotivo.setVisible(false);
                LabelMotivo.setVisible(false);
            }
        });
    }
}