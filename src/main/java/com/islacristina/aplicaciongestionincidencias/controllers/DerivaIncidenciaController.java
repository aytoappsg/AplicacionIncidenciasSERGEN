package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import com.islacristina.aplicaciongestionincidencias.services.IncidenciaService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.time.LocalDate;
import java.time.ZoneId;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador para gestionar la derivación de incidencias.
 */
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
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> comboBoxEstado;
    @FXML
    private TextArea TextAreaDescripcion;

    @Autowired
    private VerIncidenciasController verIncidenciasController;

    @Autowired
    private IncidenciaService incidenciaService;

    private Incidencia incidencia;

    private int destinatarioCount = 1;

    /**
     * Inicializa el controlador después de que se haya cargado su elemento raíz.
     *
     * @param url            La ubicación para resolver rutas relativas de recursos.
     * @param resourceBundle Los recursos localizados.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializa los campos de texto
        numOrdenField.setText("");
        autorField.setText("");
        resumenField.setText("");

        // Inicializa los ComboBox
        comboBoxEstado.setItems(FXCollections.observableArrayList("PROCEDE", "NO PROCEDE", "SUSPENDIDA", "PENDIENTE", "DERIVA"));

        // Configura los manejadores de eventos de los botones
        buttonAddDestinatario.setOnAction(this::buttonAddDestinatarioClicked);
        deshacerButton.setOnAction(this::deshacerButtonClicked);
        importarButton.setOnAction(this::importarButtonClicked);
        buttonVolver.setOnAction(this::buttonVolverClicked);
        buttonDerivar.setOnAction(this::buttonDerivarClicked);

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

        // Inicializa la cuenta de destinatarios
        destinatarioCount = 1;
    }

    /**
     * Establece la incidencia que se va a derivar.
     *
     * @param incidencia La incidencia a derivar.
     */
    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;

        numOrdenField.setText(String.valueOf(incidencia.getNumOrden()));
        autorField.setText(incidencia.getAutor());
        resumenField.setText(incidencia.getResumen());
        TextAreaDescripcion.setText(incidencia.getDescripcionIncidencia());
        ComboBoxTipo.setValue(incidencia.getTipo());
        ComboBoxAplicado.setValue(incidencia.getAplicadoA());

        // Asignar LocalDate directamente
        if (incidencia.getFechaNotificacion() != null) {
            DateFechaNoti.setValue(incidencia.getFechaNotificacion());
        }

        if (incidencia.getFechaServiciosGenerales() != null) {
            DateFechaServGral.setValue(incidencia.getFechaServiciosGenerales());
        }

        if (incidencia.getArchivos() != null) {
            listViewArchivos.setItems(FXCollections.observableArrayList(incidencia.getArchivos()));
        } else {
            listViewArchivos.setItems(FXCollections.observableArrayList());
        }
    }

    /**
     * Maneja el evento de hacer clic en el botón de importar.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void importarButtonClicked(ActionEvent event) {
        // Abre un diálogo para seleccionar un archivo PDF y lo importa
        // a la ubicación especificada, permitiendo reemplazar archivos existentes si es necesario.
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

    /**
     * Maneja el evento de hacer clic en el botón de deshacer.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void deshacerButtonClicked(ActionEvent event) {
        // Elimina el último archivo importado de la lista y del sistema de archivos.
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

    /**
     * Maneja el evento de hacer clic en el botón de volver.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void buttonVolverClicked(ActionEvent event) {
        // Vuelve a la vista de ver incidencias.
        verIncidenciasController.updateView("/verIncidencias.fxml");
    }

    /**
     * Maneja el evento de hacer clic en el botón de derivar.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void buttonDerivarClicked(ActionEvent event) {
        incidencia.setAutor(autorField.getText());
        incidencia.setResumen(resumenField.getText());
        incidencia.setTipo(ComboBoxTipo.getValue());
        incidencia.setAplicadoA(ComboBoxAplicado.getValue());

        // Convertir LocalDate a Date antes de asignarlo
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

        incidencia.setArchivos(listViewArchivos.getItems());

        // Añade esta línea para guardar el contenido del TextAreaDescripcion
        incidencia.setDescripcionIncidencia(TextAreaDescripcion.getText());

        incidenciaService.saveIncidencia(incidencia);
    }

    /**
     * Maneja el evento de hacer clic en el botón de agregar destinatario.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void buttonAddDestinatarioClicked(ActionEvent event) {
        // Agrega dinámicamente un nuevo destinatario y su ComboBox correspondiente.
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