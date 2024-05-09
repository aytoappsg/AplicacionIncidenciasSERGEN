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

/**
 * Controlador para la ventana de opciones de administrador.
 */
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
    @FXML
    private Button addPlaceButton; // Nuevo botón para agregar lugar

    private ObservableList<Usuario> usuarios;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Método para agregar un nuevo usuario.
     */
    @FXML
    private void addUser() {
        loadFXML("/register_user.fxml");
    }

    /**
     * Método para agregar un nuevo lugar.
     */
    @FXML
    private void addPlace() {
        loadFXML("/lugares_view.fxml");
    }

    /**
     * Método para modificar un usuario existente.
     */
    @FXML
    private void modifyUser() {
        Usuario selectedUsuario = usersTable.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            showAlert(Alert.AlertType.WARNING, "Warning Dialog", "No se seleccionó ningún usuario");
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
            showAlert(Alert.AlertType.ERROR, "Error Dialog", "Error al cargar la vista de modificación", e.getMessage());
        }
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

            usuarios.setAll(usuarioService.getAllUsers());
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error Dialog", "Error al cargar la vista", e.getMessage());
        }
    }

    /**
     * Método para eliminar un usuario.
     */
    @FXML
    private void deleteUser() {
        Usuario selectedUsuario = usersTable.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            showAlert(Alert.AlertType.WARNING, "Warning Dialog", "No se seleccionó ningún usuario");
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

    /**
     * Inicializa el controlador después de que se haya cargado su elemento raíz.
     *
     * @param url            La ubicación para resolver rutas relativas de recursos.
     * @param resourceBundle Los recursos localizados.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarios = FXCollections.observableArrayList(usuarioService.getAllUsers());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        usersTable.setItems(usuarios);

        addUserButton.setOnAction(event -> addUser());
        deleteUserButton.setOnAction(event -> deleteUser());
        modifyUserButton.setOnAction(event -> modifyUser());
        addPlaceButton.setOnAction(event -> addPlace());

        // Hacer el botón de Modificar Usuario invisible
        modifyUserButton.setVisible(false);
    }

    /**
     * Muestra un diálogo de alerta con el tipo, título y mensaje especificados.
     *
     * @param type    El tipo de alerta.
     * @param title   El título del diálogo.
     * @param message El mensaje a mostrar.
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        showAlert(type, title, message, null);
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
