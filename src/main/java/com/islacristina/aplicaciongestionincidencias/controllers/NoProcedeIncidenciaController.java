package com.islacristina.aplicaciongestionincidencias.controllers;

import javafx.collections.FXCollections;
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

/**
 * Controlador para la vista de una incidencia que no procede.
 */
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

    // Controladores principales
    @Autowired
    private MainController mainController;
    @Autowired
    private VerIncidenciasController verIncidenciasController;

    /**
     * Inicializa el controlador después de que se haya cargado su elemento raíz.
     *
     * @param url            La ubicación para resolver rutas relativas de recursos.
     * @param resourceBundle Los recursos localizados.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Deshabilitar todos los controles
        ComboBoxAplicadoNoProcede.setDisable(true);
        ComboBoxTipoNoProcede.setDisable(true);
        DateFechaNotiNoProcede.setDisable(true);
        DateFechaServGralNoProcede.setDisable(true);
        TextAreaDescripcionNoProcede.setDisable(true);
        TextAreaObservacionesNoProcede.setDisable(true);
        tfNumReferenciaNoProcede.setDisable(true);
        tfPrefijoNumRefNoProcede.setDisable(true);

        // Habilitar TextAreaMotivo
        TextAreaMotivoNoProcede.setDisable(false);
        ButtonVolverNoProcede.setDisable(false);

        // Inicializar los campos de texto
        tfNumReferenciaNoProcede.setText("");
        tfPrefijoNumRefNoProcede.setText("");
        TextAreaDescripcionNoProcede.setText("");

        // Inicializar los ComboBox
        comboBoxEstado.setItems(FXCollections.observableArrayList("PROCEDE", "NO PROCEDE", "SUSPENDIDA", "PENDIENTE", "DERIVA"));

        // Configurar los manejadores de eventos de los botones
        ButtonVolverNoProcede.setOnAction(this::buttonVolverClicked);

        // Agregar un listener para cambiar la clase CSS basado en el estado seleccionado
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

    /**
     * Establece el controlador principal.
     *
     * @param mainController El controlador principal.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Maneja la acción del botón "Volver".
     *
     * @param actionEvent El evento de acción.
     */
    public void handleButtonVolverAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bienvenida.fxml"));
            Parent root = loader.load();
            mainController.addStackPaneChildren(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Maneja el clic en el botón "Volver".
     *
     * @param event El evento de acción.
     */
    @FXML
    private void buttonVolverClicked(ActionEvent event) {
        verIncidenciasController.updateView("/verIncidencias.fxml");
    }
}
