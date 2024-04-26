package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import com.islacristina.aplicaciongestionincidencias.services.UsuarioService;
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
    private TableView<Usuario> usersTable;
    @FXML
    private TableColumn<Usuario, String> nameColumn;
    @FXML
    private TableColumn<Usuario, String> roleColumn;
    @FXML
    private Button addUserButton;
    @FXML
    private Button deleteUserButton;
    @FXML
    private Button modifyUserButton;

    private ObservableList<Usuario> usuarios;

    @Autowired
    private UsuarioService usuarioService;

    @FXML
    private void addUser() {
        loadFXML("/register_user.fxml");
    }

    @FXML
    private void modifyUser() {
        Usuario selectedUsuario = usersTable.getSelectionModel().getSelectedItem();

        if (selectedUsuario == null) {
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
            controller.setUser(selectedUsuario);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            if (controller.isModified()) {
                usuarioService.updateUser(selectedUsuario);
            }

            usuarios.setAll(usuarioService.getAllUsers());
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

            usuarios.setAll(usuarioService.getAllUsers());
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
        Usuario selectedUsuario = usersTable.getSelectionModel().getSelectedItem();

        if (selectedUsuario == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("No se seleccionó ningún usuario");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Estás a punto de eliminar el usuario " + selectedUsuario.getName());
        alert.setContentText("¿Estás seguro de que quieres continuar?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            usuarioService.deleteUser(selectedUsuario);
            usuarios.remove(selectedUsuario);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarios = FXCollections.observableArrayList(usuarioService.getAllUsers());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        usersTable.setItems(usuarios);

        addUserButton.setOnAction(event -> addUser());
        deleteUserButton.setOnAction(event -> deleteUser());
        modifyUserButton.setOnAction(event -> modifyUser());

        // Hacer el botón de Modificar Usuario invisible
        modifyUserButton.setVisible(false);
    }
}