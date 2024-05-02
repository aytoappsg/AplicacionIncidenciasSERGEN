package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Lugar;
import com.islacristina.aplicaciongestionincidencias.services.LugarService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication.applicationContext;

/**
 * Controlador para la gestión de lugares.
 */
@Controller
public class LugarController implements Initializable {

    @FXML
    private TableView<Lugar> placesTable;
    @FXML
    private TableColumn<Lugar, String> placeColumn;
    @FXML
    private Button addPlaceButton;
    @FXML
    private Button deletePlaceButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextField filterField;

    private ObservableList<Lugar> lugares;

    @Autowired
    private LugarService lugarService;

    /**
     * Método para agregar un lugar.
     */
    @FXML
    public void addPlace() {
        loadFXML("/registrar_lugar.fxml");
    }

    /**
     * Método para eliminar un lugar.
     */
    @FXML
    public void deletePlace() {
        Lugar selectedPlace = placesTable.getSelectionModel().getSelectedItem();

        if (selectedPlace == null) {
            showAlert(Alert.AlertType.WARNING, "Diálogo de advertencia", "No se seleccionó ningún lugar");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de confirmación");
        alert.setHeaderText("Estás a punto de eliminar el lugar " + selectedPlace.getNombreLugar());
        alert.setContentText("¿Estás seguro de que quieres continuar?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            lugarService.deleteLugar(selectedPlace);
            lugares.remove(selectedPlace);
        }
    }

    /**
     * Método para cerrar la ventana.
     */
    @FXML
    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Método para cargar un archivo FXML.
     *
     * @param fxmlFile El archivo FXML a cargar.
     */
    private void loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setControllerFactory(applicationContext::getBean);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            lugares.setAll(lugarService.getAllLugares());
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Diálogo de error", "Error al cargar la vista", e.getMessage());
        }
    }

    /**
     * Inicializa el controlador después de que se haya cargado su elemento raíz.
     *
     * @param url            La ubicación para resolver rutas relativas de recursos.
     * @param resourceBundle Los recursos localizados.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("nombreLugar"));
        lugares = FXCollections.observableArrayList(lugarService.getAllLugares());

        // 1. Envolver la ObservableList en una FilteredList (inicialmente mostrar todos los datos).
        FilteredList<Lugar> filteredData = new FilteredList<>(lugares, b -> true);

        // 2. Establecer el predicado del filtro cada vez que cambia el filtro.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(lugar -> {
                // Si el texto del filtro está vacío, mostrar todas las personas.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Comparar el nombre del lugar con el texto del filtro.
                String lowerCaseFilter = newValue.toLowerCase();

                if (lugar.getNombreLugar().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // El filtro coincide con el nombre del lugar.
                } else {
                    return false; // No coincide.
                }
            });
        });

        // 3. Envolver la FilteredList en una SortedList.
        SortedList<Lugar> sortedData = new SortedList<>(filteredData);

        // 4. Vincular el comparador de SortedList al comparador de TableView.
        sortedData.comparatorProperty().bind(placesTable.comparatorProperty());

        // 5. Agregar datos ordenados (y filtrados) a la tabla.
        placesTable.setItems(sortedData);

        exitButton.setOnAction(event -> exit());
        deletePlaceButton.setOnAction(event -> deletePlace());
        addPlaceButton.setOnAction(event -> addPlace());
    }

    /**
     * Muestra un diálogo de alerta con el tipo, título y mensaje especificados.
     *
     * @param type    El tipo de alerta.
     * @param title   El título del diálogo.
     * @param message El mensaje a mostrar.
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Muestra un diálogo de alerta con el tipo, título, mensaje y detalle especificados.
     *
     * @param type    El tipo de alerta.
     * @param title   El título del diálogo.
     * @param message El mensaje a mostrar.
     * @param detail  Los detalles adicionales.
     */
    private void showAlert(Alert.AlertType type, String title, String message, String detail) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        if (detail != null) {
            alert.setResizable(true);
            TextArea textArea = new TextArea(detail);
            alert.getDialogPane().setExpandableContent(textArea);
        }
        alert.showAndWait();
    }
}