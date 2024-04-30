package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private AnchorPane anchorPane;

    private Incidencia incidencia;

    private int destinatarioCount = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializa los campos de texto
        numOrdenField.setText("");
        autorField.setText("");
        resumenField.setText("");

        // Inicializa los ComboBox
        // comboBoxDestinatarios.setItems(FXCollections.observableArrayList("Destinatario1", "Destinatario2", "Destinatario3")); // Ejemplo

        // Configura los manejadores de eventos de los botones
        buttonAddDestinatario.setOnAction(this::buttonAddDestinatarioClicked);
        deshacerButton.setOnAction(this::deshacerButtonClicked);
        importarButton.setOnAction(this::importarButtonClicked);
        buttonVolver.setOnAction(this::buttonVolverClicked);
        buttonDerivar.setOnAction(this::buttonDerivarClicked);

        // Inicializa la cuenta de destinatarios
        destinatarioCount = 1;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
        numOrdenField.setText(String.valueOf(incidencia.getNumOrden()));
        autorField.setText(incidencia.getAutor());
        resumenField.setText(incidencia.getResumen());
        // Haz esto para cada campo de la interfaz de usuario que corresponda a una propiedad de la incidencia
    }

    @FXML
    private void importarButtonClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Files.copy(selectedFile.toPath(), Paths.get("C:\\Users\\Miguel\\Desktop\\prueba guardar\\" + selectedFile.getName()));
                listViewArchivos.getItems().add(selectedFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
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
        // Obtén la ventana actual
        Stage stage = (Stage) buttonVolver.getScene().getWindow();
        // Cierra la ventana
        stage.close();
    }

    @FXML
    private void buttonDerivarClicked(ActionEvent event) {
        // Aquí puedes agregar la lógica para derivar la incidencia
    }

    @FXML
    private void buttonAddDestinatarioClicked(ActionEvent event) {
        if (destinatarioCount < 3) {
            // Clonar otro destinatario junto con su ComboBox
            Label nuevoLabel = new Label("Destinatario " + (destinatarioCount + 1));
            nuevoLabel.setLayoutX(labelDestinatario.getLayoutX());
            nuevoLabel.setLayoutY(labelDestinatario.getLayoutY() + (30 * destinatarioCount)); // Ajusta la posición Y

            ComboBox<String> nuevoComboBox = new ComboBox<>();
            nuevoComboBox.setLayoutX(comboBoxDestinatarios.getLayoutX());
            nuevoComboBox.setLayoutY(comboBoxDestinatarios.getLayoutY() + (30 * destinatarioCount)); // Ajusta la posición Y
            nuevoComboBox.setPrefWidth(comboBoxDestinatarios.getPrefWidth());

            // Agrega el nuevo Label y ComboBox al AnchorPane
            anchorPane.getChildren().addAll(nuevoLabel, nuevoComboBox);

            destinatarioCount++;
        }
    }
}