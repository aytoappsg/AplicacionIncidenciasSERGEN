package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import com.islacristina.aplicaciongestionincidencias.services.IncidenciaService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class DerivaIncidenciaController implements Initializable {

    @FXML
    private TextField numOrdenField;

    @FXML
    private TextField autorField;

    @FXML
    private TextField resumenField;

    @FXML
    private ComboBox<String> ComboBoxTipo;

    @FXML
    private ComboBox<String> ComboBoxAplicado;

    @FXML
    private DatePicker DateFechaNoti;

    @FXML
    private DatePicker DateFechaServGral;

    @FXML
    private Button buttonAddDestinatario;

    @FXML
    private Button deshacerButton;

    @FXML
    private Button importarButton;

    @FXML
    private Button buttonVolver;

    @FXML
    private Button buttonDerivar;

    @FXML
    private ListView listViewArchivos;

    @FXML
    private Label labelDestinatario;

    @FXML
    private ComboBox comboBoxDestinatarios;

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private ComboBox<String> comboBoxEstado;
    @FXML
    private Label lastAddedLabel;
    @FXML
    private ComboBox<String> lastAddedComboBox;

    @FXML
    private TextArea TextAreaDescripcion;

    @Autowired
    private VerIncidenciasController verIncidenciasController;

    @Autowired
    private IncidenciaService incidenciaService;


    private Incidencia incidencia;

    private int destinatarioCount = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numOrdenField.setText("");
        autorField.setText("");
        resumenField.setText("");

        comboBoxEstado.setItems(FXCollections.observableArrayList("PROCEDE", "NO PROCEDE", "SUSPENDIDA", "PENDIENTE", "DERIVA"));

        buttonAddDestinatario.setOnAction(this::buttonAddDestinatarioClicked);
        deshacerButton.setOnAction(this::deshacerButtonClicked);
        importarButton.setOnAction(this::importarButtonClicked);
        buttonVolver.setOnAction(this::buttonVolverClicked);
        buttonDerivar.setOnAction(this::buttonDerivarClicked);

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

        destinatarioCount = 1;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;

        numOrdenField.setText(String.valueOf(incidencia.getNumOrden()));
        autorField.setText(incidencia.getUsuario().getName());
        //resumenField.setText(incidencia.getResumen());
        TextAreaDescripcion.setText(incidencia.getDescripcionIncidencia());
        //ComboBoxTipo.setValue(incidencia.getTipo());
        //ComboBoxAplicado.setValue(incidencia.getAplicadoA());

        if (incidencia.getFechaNotificacion() != null) {
            //DateFechaNoti.setValue(incidencia.getFechaNotificacion());
        }

        if (incidencia.getFechaServiciosGenerales() != null) {
            //DateFechaServGral.setValue(incidencia.getFechaServiciosGenerales());
        }

        /*if (incidencia.getArchivos() != null) {
            listViewArchivos.setItems(FXCollections.observableArrayList(incidencia.getArchivos()));
        } else {
            listViewArchivos.setItems(FXCollections.observableArrayList());
        }*/
    }

    @FXML
    private void importarButtonClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Path destinationPath = Paths.get("C:\\Users\\Miguel\\Desktop\\prueba guardar\\" + selectedFile.getName());
            if (Files.exists(destinationPath)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("El archivo ya existe");
                alert.setContentText("¿Deseas reemplazar el archivo existente?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    try {
                        Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                        listViewArchivos.getItems().add(selectedFile.getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    Files.copy(selectedFile.toPath(), destinationPath);
                    listViewArchivos.getItems().add(selectedFile.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void deshacerButtonClicked(ActionEvent event) {
        int lastIndex = listViewArchivos.getItems().size() - 1;
        if (lastIndex >= 0) {
            String fileName = (String) listViewArchivos.getItems().get(lastIndex);
            Path filePath = Paths.get("C:\\Users\\Miguel\\Desktop\\prueba guardar\\" + fileName);
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            listViewArchivos.getItems().remove(lastIndex);
        }
    }

    @FXML
    private void buttonVolverClicked(ActionEvent event) {
        verIncidenciasController.updateView("/verIncidencias.fxml");
    }

    @FXML
    private void buttonDerivarClicked(ActionEvent event) {
        //incidencia.setAutor(autorField.getText());
        //incidencia.setResumen(resumenField.getText());
        //incidencia.setTipo(ComboBoxTipo.getValue());
        //incidencia.setAplicadoA(ComboBoxAplicado.getValue());

        if (DateFechaNoti.getValue() != null) {
            Date dateNoti = Date.from(DateFechaNoti.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            incidencia.setFechaNotificacion(dateNoti);
        } else {
            incidencia.setFechaNotificacion(null);
        }

        if (DateFechaServGral.getValue() != null) {
            Date dateServGral = Date.from(DateFechaServGral.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            incidencia.setFechaServiciosGenerales(dateServGral);
        } else {
            incidencia.setFechaServiciosGenerales(null);
        }

        //incidencia.setArchivos(listViewArchivos.getItems());

        incidencia.setDescripcionIncidencia(TextAreaDescripcion.getText());

        incidenciaService.saveIncidencia(incidencia);
    }

    @FXML
    private void buttonAddDestinatarioClicked(ActionEvent event) {
        if (destinatarioCount < 3) {
            Label nuevoLabel = new Label("Destinatario " + (destinatarioCount + 1));
            nuevoLabel.setLayoutX(lastAddedLabel.getLayoutX());
            nuevoLabel.setLayoutY(lastAddedLabel.getLayoutY() + 30);

            ComboBox<String> nuevoComboBox = new ComboBox<>();
            nuevoComboBox.setLayoutX(lastAddedComboBox.getLayoutX());
            nuevoComboBox.setLayoutY(lastAddedComboBox.getLayoutY() + 30);
            nuevoComboBox.setPrefWidth(lastAddedComboBox.getPrefWidth());

            if (anchorPane1 != null) {
                anchorPane1.getChildren().addAll(nuevoLabel, nuevoComboBox);
            } else {
                System.out.println("anchorPane1 es null");
            }

            lastAddedLabel = nuevoLabel;
            lastAddedComboBox = nuevoComboBox;

            destinatarioCount++;
        }
    }
}