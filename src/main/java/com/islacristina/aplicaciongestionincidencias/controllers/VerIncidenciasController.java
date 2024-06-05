package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import com.islacristina.aplicaciongestionincidencias.services.IncidenciaService;
import com.islacristina.aplicaciongestionincidencias.services.VerIncidenciasService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication.applicationContext;

/**
 * Controlador para visualizar incidencias.
 */
@Getter
@Setter
@Controller
public class VerIncidenciasController implements Initializable {

    private final String GRIS = "-fx-background-color: rgb(130, 122, 121); -fx-font-weight: bold;  -fx-text-fill: WHITE; -fx-alignment: CENTER";

    private final String ROJO = "-fx-background-color: rgb(230, 0, 0); -fx-font-weight: bold;  -fx-text-fill: WHITE; -fx-alignment: CENTER";

    private final String AMARILLO = "-fx-background-color: rgb(255, 203, 61); -fx-font-weight: bold;  -fx-text-fill: WHITE; -fx-alignment: CENTER";

    private final String MORADO = "-fx-background-color: rgb(255, 0, 251); -fx-font-weight: bold;  -fx-text-fill: WHITE; -fx-alignment: CENTER";

    private final String AZUL = "-fx-background-color: rgba(135,214,227,255); -fx-font-weight: bold; -fx-text-fill: WHITE; -fx-alignment: CENTER";

    private final String NARANJA = "-fx-background-color: rgb(255, 111, 0); -fx-font-weight: bold;  -fx-text-fill: WHITE; -fx-alignment: CENTER";

    private final String VERDE = "-fx-background-color: rgb(33, 217, 30); -fx-font-weight: bold;  -fx-text-fill: WHITE; -fx-alignment: CENTER";



    @Autowired
    private DashboardController dashboardController;

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private VerIncidenciasService verIncidenciasService;

    @FXML
    private TableView<Map> incidenciasTable;

    @FXML
    private TableColumn<Incidencia, String> numOrdenColumn;

    @FXML
    private TableColumn<Incidencia, String> nuestraReferenciaColumn;

    @FXML
    private TableColumn<Incidencia, String> numRegEntradaColumn;

    @FXML
    private TableColumn<Incidencia, String> fechaDeNotificacionColumn;

    @FXML
    private TableColumn<Incidencia, String> fechaSerGenColumn;

    @FXML
    private TableColumn<Incidencia, String> tipoColumn;

    @FXML
    private TableColumn<Incidencia, String> aplicadoAColumn;

    @FXML
    private TableColumn<Incidencia, String> estadoColumn;

    @FXML
    private TableColumn<Incidencia, String> fechaAutorizacionColumn;

    @FXML
    private TableColumn<Incidencia, String> situacionColumn;

    @FXML
    private TableColumn<Incidencia, String> descripcionColumn;

    private final String colNumOrden = "numOrden";
    private final String colNuestraReferencia = "nuestraReferencia";
    private final String colNumRegEntrada = "numRegistroAyto";

    private final String colFechaDeNotificacion = "fechaNotificacion";
    private final String colFechaSerGen = "fechaSerGen";
    private final String colTipo = "tipo";
    private final String colAplicadoA = "aplicadoA";

    private final String colEstado = "estado";

    private final String colFechaAutorizacion = "fechaAutorizacion";

    private final String colSituacion = "situacion";

    private final String colDescripcion = "descripcion";



    /*
     * Método para llenar la tabla con las incidencias. (NO TOCAR)
     */
    private void llenarTabla() {
        ObservableList<Map> incidencias = verIncidenciasService.getAllIncidencias();

        numOrdenColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("numOrden")).asString();
        });

        nuestraReferenciaColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("nuestraReferencia")).asString();
        });

        numRegEntradaColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("numRegistroAyto")).asString();
        });

        fechaDeNotificacionColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("fechaNotificacion")).asString();
        });

        fechaSerGenColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("fechaServiciosGenerales")).asString();
        });

        tipoColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("tipoLugar")).asString();
        });

        aplicadoAColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("nombreLugar")).asString();
        });

        estadoColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("estado")).asString();
        });

        estadoColumn.setCellFactory(column -> {
            return new TableCell<Incidencia, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem());
                    setGraphic(null);

                    if (!isEmpty()) {
                        if (item.equals("PENDIENTE")) {
                            setStyle(GRIS);
                        } else if (item.equals("NO PROCEDE")) {
                            setStyle(ROJO);
                        } else if (item.equals("SUSPENDIDO")) {
                            setStyle(MORADO);
                        } else if (item.equals("DERIVA")) {
                            setStyle(AZUL);
                        } else if (item.equals("PROCEDE")) {
                            setStyle(VERDE);
                        } else if (item.equals("PRESENTADA")) {
                            setStyle(AMARILLO);
                        } else if (item.equals("REPETIDA")) {
                            setStyle(NARANJA);
                        } else {
                            setStyle("-fx-font-weight: bold;");
                        }
                    }
                }
            };
        });


        fechaAutorizacionColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("fechaAutorizacion")).asString();
        });

        situacionColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("situacion")).asString();
        });

        descripcionColumn.setCellValueFactory(data -> {
            Map<String, Object> value = (Map<String, Object>) data.getValue();
            return new SimpleObjectProperty<>(value.get("descripcionIncidencia")).asString();
        });

        incidenciasTable.setItems(incidencias);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llenarTabla();

        incidenciasTable.setRowFactory(tv -> {
            TableRow<Map> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    Map<String, Object> rowData = row.getItem();
                    int numOrden = (Integer) rowData.get("numOrden");

                    // Guardar la incidencia completa en incidenciaSeleccionada
                   Incidencia incidenciaSeleccionada = incidenciaService.getIncidenciaByNumOrden(numOrden);

                    System.out.println("Incidencia seleccionada: " + incidenciaSeleccionada.getNumOrden());



                    dashboardController.loadResumen(incidenciaSeleccionada);
                }
            });
            return row;
        });
    }
}