package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.*;
import com.islacristina.aplicaciongestionincidencias.services.EstadoIncidenciaService;
import com.islacristina.aplicaciongestionincidencias.services.IncidenciaService;
import com.islacristina.aplicaciongestionincidencias.services.UsuarioService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.beans.value.ChangeListener;
import java.net.URL;
import java.util.ArrayList;
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

    private Usuario usuarioActual;

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstadoIncidenciaService estadoIncidenciaService;

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public InsertarIncidenciaController() {
    }

    @FXML
    public void enviarIncidencia() {
        if (validacion()) {
            if (confirmarNoDuplicado()) {
                crearIncidencia();
                System.out.println("incidencia enviada");
            }
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
        List<TipoLugar> listaTipoLugar = incidenciaService.getAllTipoUbicacion();
        ObservableList<String> observableListTipoUbicacion = FXCollections.observableArrayList();


        for (TipoLugar tipoLugar : listaTipoLugar) {
            observableListTipoUbicacion.add(tipoLugar.getTipoLugar());
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
        // Crear una nueva instancia de Incidencia
        Incidencia nuevaIncidencia = new Incidencia();

        // Establecer los valores de las propiedades de la nueva incidencia
        nuevaIncidencia.setNuestraIncidencia(tfPrefijoNumRef.getText() + tfNumReferencia.getText());
        nuevaIncidencia.setNumRegistroAyuntamiento(tfNumRegAyto.getText());
        nuevaIncidencia.setNumExpedienteAyuntamiento(tfNumExpAyto.getText());
        nuevaIncidencia.setFechaNotificacion(java.sql.Date.valueOf(dpFechaNotificacion.getValue()));
        nuevaIncidencia.setFechaServiciosGenerales(java.sql.Date.valueOf(dpFechaServGen.getValue()));
        nuevaIncidencia.setDescripcionIncidencia(txtDescripcion.getText());

        String tipoProcedenciaSeleccionado = cbProcedencia.getValue();

        //Crear Procedencia
        Procedencia procedencia = incidenciaService.getAllProcedencia().stream()
                .filter(p -> p.getTipoProcedencia().equals(tipoProcedenciaSeleccionado))
                .findFirst()
                .orElse(null);

        nuevaIncidencia.setProcedencia(procedencia);

        //Crear un tercero
        Tercero tercero = new Tercero();
        tercero.setDniCif(tfDniCifTercero.getText());
        tercero.setEmail(txtCorreoTercero.getText());
        tercero.setTelefono(txtTelefonoTercero.getText());
        tercero.setNombre(txtNombreTercero.getText());

        tercero = incidenciaService.saveTercero(tercero);

        nuevaIncidencia.setTercero(tercero);

        //Crear un usuario
        nuevaIncidencia.setUsuario(usuarioActual);

        incidenciaService.saveIncidencia(nuevaIncidencia);

        // Guardar la incidencia en la base de datos


        //coge los valores de los combobox y los guarda en variables
        String tipoLugarSeleccionado = cbTipoUbicacion.getValue();
        String lugarSeleccionado = cbUbicado.getValue();

        //Se obtiene el objeto asociado al nombre seleccionado en el comboBox
       TipoLugar tipoLugar = incidenciaService.findByNombreTipoLugar(tipoLugarSeleccionado);
        Lugar lugar = incidenciaService.findByNombreLugar(lugarSeleccionado);

        //Se obtiene la ubicación que tienen ese idTipoLugar y idLugar
       List<Ubicacion> ubicaciones = incidenciaService.findByTipoLugarAndLugar(tipoLugar, lugar);
        //Se añade la ubicacion a la ubicacionIncidencia que une las tablas incidencia y ubicacion
       //  Ubicacion ubicacion = ubicaciones.get(0);

        //UbicacionIncidencia ubicacionIncidencia = new UbicacionIncidencia();
       // ubicacionIncidencia.setIncidencia(incidenciaService.saveIncidencia(nuevaIncidencia));
        //ubicacionIncidencia.setUbicacion(ubicacion);

        // Guardar la UbicacionIncidencia en la base de datos
       // incidenciaService.saveUbicacionIncidencia(ubicacionIncidencia);

    }
    private boolean validarFechas() {
        if (dpFechaNotificacion.getValue() == null || dpFechaServGen.getValue() == null) {
            mostrarAlerta("Error", "LAS FECHAS DE NOTIFICACIÓN Y DE SERVICIOS GENERALES SON CAMPOS OBLIGATORIOS.");
            return false;
        } else if (dpFechaNotificacion.getValue().isAfter(dpFechaServGen.getValue())) {
            mostrarAlerta("Error", "LA FECHA DE NOTIFICACIÓN NO PUEDE SER POSTERIOR A LA DE SERVICIOS GENERALES.");
            return false;
        }
        return true;
    }

    private boolean validarCorreo() {
        String correo = txtCorreoTercero.getText();
        String regex = "^[\\w-]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$";

        if ((correo == null || correo.trim().isEmpty()) || correo.matches(regex)) {
            return true;
        } else {
            mostrarAlerta("Error", "EL FORMATO DEL CORREO INTRODUCIDO NO ES CORRECTO");
            return false;
        }
    }

    private boolean validarTelefono() {
        String telefono = txtTelefonoTercero.getText();

        if ((telefono == null || telefono.trim().isEmpty()) || telefono.length() == 9) {
            return true;
        } else {
            mostrarAlerta("Error", "EL NÚMERO DE TELÉFONO INTRODUCIDO NO ES VÁLIDO");
            return false;
        }
    }

    private boolean validarDniCif() {
        String dniCif = tfDniCifTercero.getText().toUpperCase();
        String regexDni = "\\d{8}[A-HJ-NP-TV-Z]";
        String regexCif = "[ABCDEFGHJNPQRSUVW]{1}\\d{7}[0-9,A-J]";

        if ((dniCif == null || dniCif.trim().isEmpty()) || dniCif.matches(regexDni) || dniCif.matches(regexCif)) {
            return true;
        } else {
            mostrarAlerta("Error", "EL FORMATO DEL DNI/CIF INTRODUCIDO NO ES CORRECTO");
            return false;
        }
    }

    private boolean validarDescripcion() {
        String descripcion = txtDescripcion.getText();

        if (descripcion.length() > 250) {
            mostrarAlerta("Error", "LA DESCRIPCIÓN NO PUEDE OCUPAR MÁS DE 250 CARACTERES.");
            return false;
        }

        return true;
    }

    private boolean validarProcedencia() {
        if (cbProcedencia.getValue() == null || cbProcedencia.getValue().trim().isEmpty()) {
            mostrarAlerta("Error", "EL CAMPO 'MEDIO DE RECEPCIÓN' ES OBLIGATORIO.");
            return false;
        }

        if ("GESTIONA".equals(cbProcedencia.getValue())) {
            if (tfNumRegAyto.getText() == null || tfNumRegAyto.getText().trim().isEmpty() ||
                    tfNumExpAyto.getText() == null || tfNumExpAyto.getText().trim().isEmpty()) {
                mostrarAlerta("Error", "SI SELECCIONA GESTIONA, LOS CAMPOS NÚMERO DE REGISTRO Y NÚMERO DE EXPEDIENTE SON OBLIGATORIOS.");
                return false;
            }
        }

        return true;
    }

    private boolean validarDniCorreo() {
        String dni = tfDniCifTercero.getText();
        String correo = txtCorreoTercero.getText();

        if ((dni == null || dni.trim().isEmpty()) && (correo == null || correo.trim().isEmpty())) {
            mostrarAlerta("Error", "DEBE RELLENAR AL MENOS UNO DE LOS CAMPOS DNI O CORREO ELECTRÓNICO.");
            return false;
        }

        return true;
    }


    private boolean validarSiglasNumRef() {
        String siglasNumRef = tfPrefijoNumRef.getText();

        if (siglasNumRef == null || siglasNumRef.trim().isEmpty()) {
            mostrarAlerta("Error", "SI SELECCIONA GESTIONA, DEBE INTRODUCIR USTED MISMO LAS SIGLAS DEL NÚMERO DE REFERENCIA.");
            return false;
        }

        return true;
    }

    private boolean validarNumReferencia() {
        String numReferencia = tfNumReferencia.getText();

        if (numReferencia == null || numReferencia.trim().isEmpty()) {
            mostrarAlerta("Error", "EL CAMPO DEL NÚMERO DE REFERENCIA NO PUEDE ESTAR VACÍO.");
            return false;
        }

        return true;
    }

    private boolean validarUbicacion() {
        // Verificar la primera ubicación
        if (cbTipoUbicacion.getValue() == null && cbUbicado.getValue() != null) {
            mostrarAlerta("Error", "EL CAMPO 'TIPO DE UBICACIÓN' NO PUEDE ESTAR VACÍO");
            return false;
        } else if (cbTipoUbicacion.getValue() != null && cbUbicado.getValue() == null) {
            mostrarAlerta("Error", "EL CAMPO 'UBICADO' NO PUEDE ESTAR VACÍO.");
            return false;
        } else if (cbTipoUbicacion.getValue() == null && cbUbicado.getValue() == null) {
            mostrarAlerta("Error", "LOS CAMPOS DE 'LOCALIZACION/ES DE LA INCIDENCIA SON OBLIGATORIOS'");
            return false;
        }

        // Verificar la segunda ubicación si se ha añadido
        if (!cbTipoUbicacion2.isDisabled() && (cbTipoUbicacion2.getValue() == null || cbUbicado2.getValue() == null)) {
            mostrarAlerta("Error", "HA AÑADIDO UNA SEGUNDA UBICACIÓN PERO NO LA HA RELLENADO, ELIMÍNELA.");
            return false;
        }

        // Verificar la tercera ubicación si se ha añadido
        if (!cbTipoUbicacion3.isDisabled() && (cbTipoUbicacion3.getValue() == null || cbUbicado3.getValue() == null)) {
            mostrarAlerta("Error", "HA AÑADIDO UNA TERCERA UBICACIÓN PERO NO LA HA RELLENADO, ELIMÍNELA.");
            return false;
        }

        return true;
    }

    private boolean validacion() {
        return  validarDniCorreo() && validarCorreo() && validarTelefono() && validarDniCif()  && validarDescripcion() && validarProcedencia() && validarSiglasNumRef() && validarNumReferencia() && validarFechas() && validarUbicacion();
    }

    private boolean confirmarNoDuplicado() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMACIÓN");
        alert.setHeaderText("¿Ha comprobado que esta incidencia no está duplicada?");
        alert.setContentText("Por favor, asegúrese de que esta incidencia no existe antes de enviarla.");

        ButtonType buttonTypeYes = new ButtonType("Sí", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        java.util.Optional<ButtonType> result = alert.showAndWait();
        return result.get() == buttonTypeYes;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}

