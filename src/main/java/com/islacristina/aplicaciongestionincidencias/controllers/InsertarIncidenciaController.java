package com.islacristina.aplicaciongestionincidencias.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class InsertarIncidenciaController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TitledPane titledPaneTercero;

    @FXML
    private ComboBox<String> comboBoxMedioRecepcion;

    @FXML
    private Label lblDni;

    @FXML
    private TextField txtDni;

    @FXML
    private Label lblCorreo;

    @FXML
    private TextField txtCorreo;

    @FXML
    private Label lblNombre;

    @FXML
    private TextField txtNombre;

    @FXML
    private Label lblTlf;

    @FXML
    private TextField txtTlf;

    @FXML
    private TitledPane titledPaneTercero1;

    @FXML
    private GridPane gridPaneTercero1;

    @FXML
    private Label lblMedioRecepcion;

    @FXML
    private Label lblNumReferencia;

    @FXML
    private Label lblNumRegistroAyto;

    @FXML
    private TextField txtNumRegistroAyto;

    @FXML
    private Label lblNumExpedienteAyto;

    @FXML
    private TextField txtNumExpedienteAyto;

    @FXML
    private TitledPane titledPaneFechas;

    @FXML
    private DatePicker datePickerFechaNotif;

    @FXML
    private DatePicker datePickerServGral;

    @FXML
    private TitledPane titledPaneUbicacion;

    @FXML
    private ComboBox<String> comboBoxTipo;

    @FXML
    private ComboBox<String> comboBoxUbicadoA;

    @FXML
    private TextField txtUbicadoA;

    @FXML
    private void initialize() {
        // Código de inicialización
    }
}
