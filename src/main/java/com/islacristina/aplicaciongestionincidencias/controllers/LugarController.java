package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Lugar;
import com.islacristina.aplicaciongestionincidencias.services.LugarService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private ObservableList<Lugar> lugares;

    @Autowired
    private LugarService lugarService;

    @FXML
    public void addPlace() {
        loadFXML("/registrar_lugar.fxml");
    }

    @FXML
    public void deletePlace() {
        Lugar selectedPlace = placesTable.getSelectionModel().getSelectedItem();

        if (selectedPlace == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("No se seleccionó ningún lugar");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Estás a punto de eliminar el lugar " + selectedPlace.getNombreLugar());
        alert.setContentText("¿Estás seguro de que quieres continuar?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            lugarService.deleteLugar(selectedPlace);
            lugares.remove(selectedPlace);
        }
    }

    @FXML
    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error al cargar la vista");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("nombreLugar"));
        lugares = FXCollections.observableArrayList(lugarService.getAllLugares());
        placesTable.setItems(lugares);

        exitButton.setOnAction(event -> exit());
        deletePlaceButton.setOnAction(event -> deletePlace());
        addPlaceButton.setOnAction(event -> addPlace());
    }
}