package com.islacristina.aplicaciongestionincidencias.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class NoProcedeIncidenciaController implements Initializable {
    @FXML
    private ComboBox<?> ComboBoxAplicado, ComboBoxTipo, ComboBoxEstado;

    @FXML
    private DatePicker DateFechaNoti, DateFechaServGral;

    @FXML
    private TextArea TextAreaDescripcion, TextAreaObservaciones, TextAreaMotivo;

    @FXML
    private TextField tfNumReferencia, tfPrefijoNumRef;

    @FXML
    private Button ButtonVolver;
    @Autowired
    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Disable all controls
        ComboBoxAplicado.setDisable(true);
        ComboBoxTipo.setDisable(true);
        ComboBoxEstado.setDisable(false);
        DateFechaNoti.setDisable(true);
        DateFechaServGral.setDisable(true);
        TextAreaDescripcion.setDisable(true);
        TextAreaObservaciones.setDisable(true);
        tfNumReferencia.setDisable(true);
        tfPrefijoNumRef.setDisable(true);

        // Enable TextAreaMotivo
        TextAreaMotivo.setDisable(false);
        ButtonVolver.setDisable(false);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void handleButtonVolverAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bienvenida.fxml"));
            Parent root = loader.load();
            mainController.addStackPaneChildren(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}