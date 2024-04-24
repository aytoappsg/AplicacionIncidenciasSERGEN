package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.*;
import com.islacristina.aplicaciongestionincidencias.services.IncidenciaService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.event.ChangeEvent;
import javafx.beans.value.ChangeListener;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class InsertarIncidenciaController implements Initializable {
    /*
     * LOS ELEMENTOS CON LA ETIQUETA @FXML SON LOS PRESENTES EN LA PLANTILLA insertar_incidencia.fxml
     */
    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox<String> cbProcedencia;

    @FXML
    private TextField tfNumRegAyto;

    @FXML
    private TextField tfPrefijoNumRef;

    private String numReferenciaConcat;

    @FXML
    private TextField tfNumReferencia;

    @FXML
    private TextField tfNumExpAyto;

    @FXML
    private DatePicker dpFechaServGen;

    @FXML
    private DatePicker dpFechaNotificacion;

    @FXML
    private TextField tfDniCifTercero;

    @FXML
    private TextField txtNombreTercero;

    @FXML
    private TextField txtCorreoTercero;

    @FXML
    private TextField txtTelefonoTercero;

    @FXML
    private ComboBox<String> cbTipoUbicacion;

    @FXML
    private ComboBox<String> cbTipoUbicacion2;

    @FXML
    private ComboBox<String> cbTipoUbicacion3;

    @FXML
    private ComboBox<String> cbUbicado;

    @FXML
    private ComboBox<String> cbUbicado2;

    @FXML
    private ComboBox<String> cbUbicado3;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnAdd2;

    @FXML
    private Button btnEliminar2;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private Button btnEnviar;

    @Autowired
    private IncidenciaService incidenciaService;

    public InsertarIncidenciaController() {
    }

    @FXML
    public void enviarIncidencia() {
        if (validarFechas() && validarCorreo() && validarTelefono() && validarDniCif()) {
            crearIncidencia();
            System.out.println("incidencia enviada");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        establecerDatosPrevios();

        cbProcedencia.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                // Verificar la procedencia seleccionada y rellenar los campos correspondientes
                switch (newValue) {
                    case "TELÉFONO":
                        rellenarCamposTelefono();
                        break;
                    case "CORREO ELECTRÓNICO":
                        rellenarCamposCorreo();
                        break;
                    case "GESTIONA":
                        rellenarCamposGestiona();
                        break;
                    case "APP":
                        rellenarCamposApp();
                        break;
                    case "PRESENCIAL":
                        rellenarCamposPresencial();
                        break;
                    default:

                        break;
                }
            }
        });
    }

    private void establecerDatosPrevios() {
        cargarTipoProcedencia();
        cargarTiposUbicacion(cbTipoUbicacion);
        cargarLugar(cbUbicado);


    }

    //Método que carga el tipo de ubicación de la BBDD en el programa.
    private void cargarTiposUbicacion(ComboBox<String> comboBox) {
        List<TipoUbicacion> listaTipoUbicacion = incidenciaService.getAllTipoUbicacion();
        ObservableList<String> observableListTipoUbicacion = FXCollections.observableArrayList();


        for (TipoUbicacion tipoUbicacion : listaTipoUbicacion) {
            observableListTipoUbicacion.add(tipoUbicacion.getTipoProcedencia());
        }


        comboBox.setItems(observableListTipoUbicacion);
    }

    //Método que carga el tipo de procedencia de la BBDD en el desplegable del programa
    private void cargarTipoProcedencia() {
        List<Procedencia> lista = incidenciaService.getAllProcedencia();
        ObservableList<String> observableList = FXCollections.observableArrayList();

        for (Procedencia elemento : lista) {
            observableList.add(elemento.getTipoProcedencia());
        }


        cbProcedencia.setItems(observableList);
    }

    //Método que carga los lugares de la BBDD en el desplegable del programa.
    private void cargarLugar(ComboBox<String> comboBox) {
        List<Lugar> lista = incidenciaService.getAllLugar();
        ObservableList<String> observableList = FXCollections.observableArrayList();

        for (Lugar lugar : lista) {
            observableList.add(lugar.getNombreLugar());
        }

        comboBox.setItems(observableList);
    }

    private void activarUbicacionesExtra(boolean focusable) {
        cbTipoUbicacion2.setDisable(!focusable);
        cbUbicado2.setDisable(!focusable);
        btnAdd.setDisable(focusable);
        btnEliminar.setDisable(!focusable);
        cargarTiposUbicacion(cbTipoUbicacion2);
        cargarLugar(cbUbicado2);

    }

    private void activarUbicacionesExtra2(boolean focusable) {
        cbTipoUbicacion3.setDisable(!focusable);
        cbUbicado3.setDisable(!focusable);
        btnAdd2.setDisable(focusable);
        btnEliminar2.setDisable(!focusable);
        cargarTiposUbicacion(cbTipoUbicacion3);
        cargarLugar(cbUbicado3);
    }

    @FXML
    private void handleBtnAddAction() {
        activarUbicacionesExtra(true);
        cbTipoUbicacion2.requestFocus();
    }

    @FXML
    private void handleBtnAdd2Action() {
        activarUbicacionesExtra2(true);
        cbTipoUbicacion3.requestFocus();
    }

    @FXML
    private void handleBtnEliminarAction() {
        activarUbicacionesExtra(false);
        cbUbicado2.setValue(null);
        cbTipoUbicacion2.setValue(null);
    }

    @FXML
    private void handleBtnEliminar2Action() {
        activarUbicacionesExtra2(false);
        cbUbicado3.setValue(null);
        cbTipoUbicacion3.setValue(null);
    }


    private boolean validarFechas() {
        if (dpFechaNotificacion.getValue() != null && dpFechaServGen.getValue() != null) {
            if (dpFechaNotificacion.getValue().isAfter(dpFechaServGen.getValue())) {
                mostrarAlerta("Error", "La fecha de notificación no puede ser posterior a la fecha de servicios generales.");
                return false;
            }
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void rellenarCamposTelefono() {
        tfPrefijoNumRef.setText("TLF-"); // Rellenar el campo prefijo del número de referencia
        desactivarCampos();
    }

    private void rellenarCamposCorreo() {
        tfPrefijoNumRef.setText("EM-"); // Rellenar el campo prefijo del número de referencia
        desactivarCampos();
    }

    private void rellenarCamposApp() {
        tfPrefijoNumRef.setText("APP-"); // Rellenar el campo prefijo del número de referencia
        desactivarCampos();
    }

    private void rellenarCamposPresencial() {
        tfPrefijoNumRef.setText("PRE-"); // Rellenar el campo prefijo del número de referencia
        desactivarCampos();

    }

    private void rellenarCamposGestiona() {
        tfPrefijoNumRef.setText(null);
        tfPrefijoNumRef.setDisable(false);
        tfNumRegAyto.setDisable(false);
        tfNumExpAyto.setDisable(false);
    }

    private void desactivarCampos() {
        tfPrefijoNumRef.setDisable(true);
        tfNumRegAyto.setDisable(true);
        tfNumExpAyto.setDisable(true);
    }

    private void crearIncidencia() {
        /*
        Incidencia incidencia = new Incidencia();
        //DATOS DE LA INCIDENCIA
        Procedencia procedencia = new Procedencia();
        procedencia.setTipoProcedencia(cbProcedencia.getValue());
        incidencia.setProcedenciaIncidencia(procedencia.getIdProcedencia());
        System.out.println(procedencia.getIdProcedencia());

        incidencia.setNumRegistroAyuntamiento(tfNumRegAyto.getText());
        System.out.println(tfNumRegAyto.getText());
        incidencia.setNuestraIncidencia(tfPrefijoNumRef.getText() + tfNumReferencia.getText());
        System.out.println(tfPrefijoNumRef.getText() + tfNumReferencia.getText());
        incidencia.setNumExpedienteAyuntamiento(tfNumExpAyto.getText());
        System.out.println(tfNumExpAyto.getText());
        incidencia.setNumRegistroAyuntamiento(tfNumRegAyto.getText());
        System.out.println(tfNumRegAyto.getText());
        //cambiar tipo de la fecha o castearla
            //incidencia.setFechaNotificacion(dpFechaNotificacion.getValue());
            //incidencia.setFechaServiciosGenerales(dpFechaServGen.getValue());
        //DATOS DEL TERCERO
        Tercero tercero = new Tercero();
        tercero.setDniCif(txtDniTercero.getText());
        tercero.setEmail(txtCorreoTercero.getText());
        tercero.setNombre(txtNombreTercero.getText());
        tercero.setTelefono(txtTelefonoTercero.getText());
        incidencia.setTercero(tercero.getId());
        System.out.println(tercero.getDniCif());
        //DATOS DE LA UBICACIÓN
        Lugar lugar = new Lugar();
        lugar.setNombreLugar(cbUbicado.getValue());

        //DATOS DE LA DESCRIPCION
        incidencia.setDescripcionIncidencia(txtDescripcion.getText());

         */
    }

    private boolean validarCorreo() {
        String correo = txtCorreoTercero.getText();
        String regex = "^[\\w-]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$";

        if (!correo.matches(regex)) {
            mostrarAlerta("Error", "El formato del correo electrónico no es correcto.");
            return false;
        }

        return true;
    }

    private boolean validarTelefono() {
        String telefono = txtTelefonoTercero.getText();

        if (telefono.length() != 9) {
            mostrarAlerta("Error", "El número de teléfono debe contener exactamente 9 caracteres.");
            return false;
        }

        return true;
    }

    private boolean validarDniCif() {
        String dniCif = tfDniCifTercero.getText();
        String regexDni = "\\d{8}[A-HJ-NP-TV-Z]";
        String regexCif = "[ABCDEFGHJNPQRSUVW]{1}\\d{7}[0-9,A-J]";

        if (!dniCif.matches(regexDni) && !dniCif.matches(regexCif)) {
            mostrarAlerta("Error", "El formato del DNI/CIF no es correcto.");
            return false;
        }

        return true;
    }

}

