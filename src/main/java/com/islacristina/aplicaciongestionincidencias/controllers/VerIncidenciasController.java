package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import com.islacristina.aplicaciongestionincidencias.services.IncidenciaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class VerIncidenciasController implements Initializable {

    @FXML
    private TableView<Incidencia> incidenciasTable;

    @FXML
    private TableColumn<Incidencia, Integer> numOrdenColumn;

    @FXML
    private TableColumn<Incidencia, Integer> procedenciaIncidenciaColumn;

    @FXML
    private TableColumn<Incidencia, String> nuestraIncidenciaColumn;

    @FXML
    private TableColumn<Incidencia, String> numRegistroAyuntamientoColumn;

    @FXML
    private TableColumn<Incidencia, String> fechaNotificacionColumn;

    @FXML
    private TableColumn<Incidencia, String> fechaServiciosGeneralesColumn;

    @FXML
    private TableColumn<Incidencia, String> descripcionIncidenciaColumn;

    @FXML
    private TableColumn<Incidencia, Integer> usuarioColumn;

    @FXML
    private TableColumn<Incidencia, Integer> estadoIncidenciaColumn;

    @FXML
    private TableColumn<Incidencia, String> observacionColumn;

    @Autowired
    private IncidenciaService incidenciaService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numOrdenColumn.setCellValueFactory(new PropertyValueFactory<>("numOrden"));
        procedenciaIncidenciaColumn.setCellValueFactory(new PropertyValueFactory<>("procedenciaIncidencia"));
        nuestraIncidenciaColumn.setCellValueFactory(new PropertyValueFactory<>("nuestraIncidencia"));
        numRegistroAyuntamientoColumn.setCellValueFactory(new PropertyValueFactory<>("numRegistroAyuntamiento"));
        fechaNotificacionColumn.setCellValueFactory(new PropertyValueFactory<>("fechaNotificacion"));
        fechaServiciosGeneralesColumn.setCellValueFactory(new PropertyValueFactory<>("fechaServiciosGenerales"));
        descripcionIncidenciaColumn.setCellValueFactory(new PropertyValueFactory<>("descripcionIncidencia"));
        usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        estadoIncidenciaColumn.setCellValueFactory(new PropertyValueFactory<>("estadoIncidencia"));
        observacionColumn.setCellValueFactory(new PropertyValueFactory<>("observacion"));

        estadoIncidenciaColumn.setCellFactory(column -> {
            return new TableCell<Incidencia, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Translate the status number to a status string
                        String status;
                        switch (item) {
                            case 1:
                                status = "PENDIENTE";
                                break;
                            case 2:
                                status = "NO PROCEDE";
                                break;
                            case 3:
                                status = "SUSPENDIDO";
                                break;
                            case 4:
                                status = "DERIVA";
                                break;
                            case 5:
                                status = "PROCEDE";
                                break;
                            default:
                                status = "UNKNOWN";
                                break;
                        }

                        setText(status);

                        // Style the cell based on the status
                        switch (status) {
                            case "PROCEDE":
                                setTextFill(Color.GREEN);
                                break;
                            case "NO PROCEDE":
                                setTextFill(Color.RED);
                                break;
                            case "SUSPENDIDO":
                                setTextFill(Color.PURPLE);
                                break;
                            case "PENDIENTE":
                                setTextFill(Color.GRAY);
                                break;
                            case "DERIVA":
                                setTextFill(Color.SKYBLUE);
                                break;
                            default:
                                setTextFill(Color.BLACK);
                                break;
                        }
                    }
                }
            };
        });

        ObservableList<Incidencia> incidencias = FXCollections.observableArrayList(incidenciaService.getAllIncidencias());
        incidenciasTable.setItems(incidencias);
    }
}