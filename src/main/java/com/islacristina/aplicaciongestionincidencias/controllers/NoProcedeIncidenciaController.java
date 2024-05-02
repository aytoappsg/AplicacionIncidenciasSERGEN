package com.islacristina.aplicaciongestionincidencias.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<String> ComboBoxAplicadoNoProcede, ComboBoxTipoNoProcede, comboBoxEstado;

    @FXML
    private DatePicker DateFechaNotiNoProcede, DateFechaServGralNoProcede;

    @FXML
    private TextArea TextAreaDescripcionNoProcede, TextAreaObservacionesNoProcede, TextAreaMotivoNoProcede;

    @FXML
    private TextField tfNumReferenciaNoProcede, tfPrefijoNumRefNoProcede;

    @FXML
    private Button ButtonVolverNoProcede;
    @Autowired
    private MainController mainController;
    @Autowired
    private VerIncidenciasController verIncidenciasController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Disable all controls
        ComboBoxAplicadoNoProcede.setDisable(true);
        ComboBoxTipoNoProcede.setDisable(true);
        DateFechaNotiNoProcede.setDisable(true);
        DateFechaServGralNoProcede.setDisable(true);
        TextAreaDescripcionNoProcede.setDisable(true);
        TextAreaObservacionesNoProcede.setDisable(true);
        tfNumReferenciaNoProcede.setDisable(true);
        tfPrefijoNumRefNoProcede.setDisable(true);

        // Enable TextAreaMotivo
        TextAreaMotivoNoProcede.setDisable(false);
        ButtonVolverNoProcede.setDisable(false);

        // Inicializa los campos de texto
        tfNumReferenciaNoProcede.setText("");
        tfPrefijoNumRefNoProcede.setText("");
        TextAreaDescripcionNoProcede.setText("");

        // Inicializa los ComboBox
        comboBoxEstado.setItems(FXCollections.observableArrayList("PROCEDE", "NO PROCEDE", "SUSPENDIDA", "PENDIENTE", "DERIVA"));

        // Configura los manejadores de eventos de los botones
        ButtonVolverNoProcede.setOnAction(this::buttonVolverClicked);

        // Agrega un listener para cambiar la clase CSS basado en el estado seleccionado
        comboBoxEstado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            comboBoxEstado.getStyleClass().removeAll("combo-box-procede", "combo-box-no-procede", "combo-box-suspendido", "combo-box-pendiente", "combo-box-deriva");
            String fxmlFile;
            switch (newValue) {
                case "NO PROCEDE":
                    fxmlFile = "/noProcedeIncidencia.fxml";
                    break;
                default:
                    fxmlFile = "/" + newValue.toLowerCase().replace(" ", "") + "Incidencia.fxml";
                    break;
            }
            verIncidenciasController.updateView(fxmlFile);
        });
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
    @FXML
    private void buttonVolverClicked(ActionEvent event) {
        verIncidenciasController.updateView("/verIncidencias.fxml");
    }
}