package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Procedencia;
import com.islacristina.aplicaciongestionincidencias.repositories.ProcedenciaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class InsertarIncidenciaController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox<String> cbProcedencia;

    @FXML
    private TextField txtNumRegAyto;

    @FXML
    private DatePicker dpNotificacion;

    @FXML
    private TextField txtSiglasProcedencia;

    @FXML
    private TextField txtReferencia;

    @FXML
    private TextField txtNumExpAyto;

    @FXML
    private DatePicker dpServiciosGenerales;

    @FXML
    private TextField txtDniTercero;

    @FXML
    private TextField txtNombreTercero;

    @FXML
    private TextField txtCorreoTercero;

    @FXML
    private TextField txtTelefonoTercero;

    @FXML
    private ComboBox<String> cbTipoUbicacion1;

    @FXML
    private ComboBox<String> cbTipoUbicacion2;

    @FXML
    private ComboBox<String> cbTipoUbicacion3;

    @FXML
    private ComboBox<String> cbUbicadoAplicado1;

    @FXML
    private ComboBox<String> cbUbicadoAplicado2;

    @FXML
    private ComboBox<String> cbUbicadoAplicado3;

    @FXML
    private Button btnAnadir1;

    @FXML
    private Button btnEliminar1;

    @FXML
    private Button btnAnadir2;

    @FXML
    private Button btnEliminar2;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private Button btnEnviar;

    private ProcedenciaRepository procedenciaRepository;

    @FXML
    public void enviarIncidencia() {
        // Lógica para enviar la incidencia
    }

    @Autowired
    public InsertarIncidenciaController(ProcedenciaRepository procedenciaRepository){
        this.procedenciaRepository = procedenciaRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        establecerDatosPrevios();
    }

    private void establecerDatosPrevios() {
        List<Procedencia> lista = procedenciaRepository.findAll();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Procedencia elemento : lista){
            observableList.add(elemento.getTipoProcedencia());
        }
        cbProcedencia.setItems(observableList);
    }
}
