package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class VerIncidenciasController {

    @FXML
    private TableView<Incidencia> tableView;

    @FXML
    private TableColumn<Incidencia, String> numeroOrdenColumn;

    // Asume que tienes una clase Incidencia que representa los datos de tu tabla
    private ObservableList<Incidencia> incidenciaData = FXCollections.observableArrayList();

    public void initialize() {
        // Configura las columnas de la tabla
        numeroOrdenColumn.setCellValueFactory(new PropertyValueFactory<>("numeroOrden"));

        // Crea un FilteredList (inicialmente muestra todos los datos)
        FilteredList<Incidencia> filteredData = new FilteredList<>(incidenciaData, b -> true);

        // Configura el filtro cada vez que el texto del filtro cambia
        numeroOrdenColumn.setCellFactory(tc -> {
            TableCell<Incidencia, String> cell = new TableCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.setOnMouseClicked(event -> {
                if (! cell.isEmpty()) {
                    // Aquí puedes implementar la lógica que se ejecutará cuando se haga clic en una celda
                    // Por ejemplo, puedes filtrar los datos de la tabla basándote en el valor de la celda
                }
            });
            return cell ;
        });

        // Envuelve el FilteredList en un SortedList
        SortedList<Incidencia> sortedData = new SortedList<>(filteredData);

        // Vincula el comparador SortedList al comparador TableView
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // Agrega los datos ordenados (y filtrados) a la tabla
        tableView.setItems(sortedData);
    }
}