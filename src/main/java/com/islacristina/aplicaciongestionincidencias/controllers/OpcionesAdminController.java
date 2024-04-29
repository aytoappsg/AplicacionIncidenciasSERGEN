package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.User;
import com.islacristina.aplicaciongestionincidencias.services.UserService;
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
public class OpcionesAdminController implements Initializable {

    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private Button addUserButton;
    @FXML
    private Button deleteUserButton;
    @FXML
    private Button modifyUserButton;
    @FXML
    private Button addPlaceButton; // Nueva referencia al botón "Lugar"

    private ObservableList<User> users;

    @Autowired
    private UserService userService;

    @FXML
    private void addUser() {
        loadFXML("/register_user.fxml");
    }

    @FXML
    private void addPlace() { // Nuevo método para manejar el clic en el botón "Lugar"
        loadFXML("/lugares_view.fxml");
    }

    @FXML
    private void modifyUser() {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("No se seleccionó ningún usuario");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifyUser.fxml"));
            Parent root = loader.load();

            ModificarUsuariosController controller = loader.getController();
            controller.setUser(selectedUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            if (controller.isModified()) {
                userService.updateUser(selectedUser);
            }

            users.setAll(userService.getAllUsers());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error al cargar la vista de modificación");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setControllerFactory(applicationContext::getBean);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            users.setAll(userService.getAllUsers());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error al cargar la vista");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteUser() {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("No se seleccionó ningún usuario");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Estás a punto de eliminar el usuario " + selectedUser.getName());
        alert.setContentText("¿Estás seguro de que quieres continuar?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            userService.deleteUser(selectedUser);
            users.remove(selectedUser);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        users = FXCollections.observableArrayList(userService.getAllUsers());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        usersTable.setItems(users);

        addUserButton.setOnAction(event -> addUser());
        deleteUserButton.setOnAction(event -> deleteUser());
        modifyUserButton.setOnAction(event -> modifyUser());
        addPlaceButton.setOnAction(event -> addPlace()); // Nuevo manejador de eventos para el botón "Lugar"

        // Hacer el botón de Modificar Usuario invisible
        modifyUserButton.setVisible(false);
    }
}