package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.exceptions.InsertarIncidenciaException;
import com.islacristina.aplicaciongestionincidencias.model.*;
import com.islacristina.aplicaciongestionincidencias.services.IncidenciaService;
import com.islacristina.aplicaciongestionincidencias.util.AutoCompleteTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.stereotype.Controller;
import javafx.beans.value.ChangeListener;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Controller
public class InsertarIncidenciaController implements Initializable {

    //Atributos relacionados con los elementos de la vista
    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox<String> cbProcedencia, cbTipoUbicacion, cbTipoUbicacion2, cbTipoUbicacion3;

    @FXML
    private TextField txtNumRegAyto, tfPrefijoNumRef, tfNumReferencia, txtNumExpAyto, txtDniCifTercero, txtNombreTercero, txtCorreoTercero, txtTelefonoTercero, txtIncidenciaOrigen;

    @FXML
    private DatePicker dpFechaServGen, dpFechaNotificacion;

    @FXML
    private Button btnAdd, btnEliminar, btnAdd2, btnEliminar2, BtnEnviar;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private CheckBox checkbRepetida;

    AutoCompleteTextField txtUbicacion1 = new AutoCompleteTextField();
    AutoCompleteTextField txtUbicacion2 = new AutoCompleteTextField();
    AutoCompleteTextField txtUbicacion3 = new AutoCompleteTextField();

    //Usuario actual que será el que envíe la incidencia
    private Usuario usuarioActual;

    //Bandera que indica si se ha encontrado un tercero en la base de datos con los datos introducidos
    private boolean terceroEncontrado = false;

    //Lista independiente con las sugerencias de lugares para cada campo de ubicación
    private List<String> listaLugares1 = new ArrayList<>();
    private List<String> listaLugares2 = new ArrayList<>();
    private List<String> listaLugares3 = new ArrayList<>();

    private List<Ubicacion> ubicaciones = new ArrayList<>();

    //Atributos relacionados con la lógica de autocompletado (NO TOCAR)
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private Future<?> task1, task2, task3 = null;
    private AtomicBoolean isTyping = new AtomicBoolean(false);

    @Autowired
    private ThreadPoolTaskExecutorBuilder threadPoolTaskExecutorBuilder;

    //Service que se encarga de las llamadas a los repositorios
    @Autowired
    private IncidenciaService incidenciaService;


    /**
     * Método que se encarga de inicializar la vista, cargar los datos previos desde la vista y establecer los
     * listeners de los elementos de la vista para sus diferentes acciones.
     *
     * @param url:            ?
     * @param resourceBundle: ?
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Alerta de aviso para comprobar si la incidencia está duplicada
        alertaIncidenciaDuplicada();

        //Adición de los elementos de autocompletaddo a la vista sobre GridPane
        gridPane.add(txtUbicacion1, 3, 0);
        gridPane.add(txtUbicacion2, 3, 1);
        gridPane.add(txtUbicacion3, 3, 2);

        //Llamada a los métodos que establecen los datos previos desde la bbdd
        establecerDatosPrevios();

        //Listener del combobox de procedencia
        cbProcedencia.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                procedenciaListener(newValue);
            }
        });

        //Listener del textfield de DNI/CIF
        txtDniCifTercero.textProperty().addListener((observable, oldValue, newValue) -> {
            if (incidenciaService.getProcedenciaByTipoProcedencia(cbProcedencia.getValue()).getMetodoValidacion().equals("dni_cif")) {
                dniCifListener(newValue);
            }
        });

        //Listener del textfield de correo electrónico
        txtCorreoTercero.textProperty().addListener((observable, oldValue, newValue) -> {
            if (incidenciaService.getProcedenciaByTipoProcedencia(cbProcedencia.getValue()).getMetodoValidacion().equals("email")) {
                emailListener(newValue);
            }
        });

        //Listener del combobox de tipo de ubicación
        cbTipoUbicacion.valueProperty().addListener((observable, oldValue, newValue) -> {
            txtUbicacion1.setDisable(false);
            listaLugares1.addAll(ubicaciones.stream()
                    .filter(ubicacion ->
                            ubicacion.getTipoLugar().getTipoLugar().equals(newValue))
                    .map(ubicacion -> ubicacion.getLugar().getNombreLugar())
                    .collect(Collectors.toCollection(ArrayList::new)));


        });
        cbTipoUbicacion2.valueProperty().addListener((observable, oldValue, newValue) -> {
            listaLugares2.addAll(ubicaciones.stream()
                    .filter(ubicacion ->
                            ubicacion.getTipoLugar().getTipoLugar().equals(newValue))
                    .map(ubicacion -> ubicacion.getLugar().getNombreLugar())
                    .collect(Collectors.toCollection(ArrayList::new)));
            txtUbicacion2.setDisable(false);
        });
        cbTipoUbicacion3.valueProperty().addListener((observable, oldValue, newValue) -> {
            listaLugares3.addAll(ubicaciones.stream()
                    .filter(ubicacion ->
                            ubicacion.getTipoLugar().getTipoLugar().equals(newValue))
                    .map(ubicacion -> ubicacion.getLugar().getNombreLugar())
                    .collect(Collectors.toCollection(ArrayList::new)));
            txtUbicacion3.setDisable(false);
        });

        //Listener del texfield de autocompletado del primer campo de ubicación
        //No tocar, es la lógica de autocompletado y funciona gracias a dios
        txtUbicacion1.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() >= 2) {
                txtUbicacion1.getEntries().addAll(
                        listaLugares1.stream().
                                filter(lugar -> lugar.contains(newValue))
                                .collect(Collectors.toCollection(ArrayList::new)));
            } else {
                txtUbicacion1.getEntries().clear();
            }
        });
        txtUbicacion2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 2) {
                txtUbicacion2.getEntries().addAll(
                        listaLugares2.stream().
                                filter(lugar -> lugar.contains(newValue))
                                .collect(Collectors.toCollection(ArrayList::new)));
            } else {
                txtUbicacion2.getEntries().clear();
            }
        });
        txtUbicacion3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 2) {
                txtUbicacion3.getEntries().addAll(
                        listaLugares3.stream().
                                filter(lugar -> lugar.contains(newValue))
                                .collect(Collectors.toCollection(ArrayList::new)));
            } else {
                txtUbicacion3.getEntries().clear();
            }
        });
        checkbRepetida.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                txtIncidenciaOrigen.setDisable(false);
                cbTipoUbicacion.setDisable(true);
                txtUbicacion1.setDisable(true);
                btnAdd.setDisable(true);
                btnEliminar.setDisable(true);
                cbTipoUbicacion2.setDisable(true);
                txtUbicacion2.setDisable(true);
                btnAdd2.setDisable(true);
                btnEliminar2.setDisable(true);
                cbTipoUbicacion3.setDisable(true);
                txtUbicacion3.setDisable(true);
            } else {
                cbTipoUbicacion.setDisable(false);
                btnAdd.setDisable(false);
                if (cbTipoUbicacion2.getValue() != null && !cbTipoUbicacion2.getValue().trim().isEmpty()) {
                    txtUbicacion2.setDisable(false);
                }
                if (cbTipoUbicacion3.getValue() != null && !cbTipoUbicacion3.getValue().trim().isEmpty()) {
                    txtUbicacion3.setDisable(false);
                }
                if (txtUbicacion1.getText() != null && !txtUbicacion1.getText().trim().isEmpty()) {
                    txtUbicacion1.setDisable(false);
                }
                if (txtUbicacion2.getText() != null && !txtUbicacion2.getText().trim().isEmpty()) {
                    txtUbicacion2.setDisable(false);
                    btnAdd.setDisable(true);
                    btnEliminar.setDisable(false);
                    btnAdd2.setDisable(false);
                    btnEliminar2.setDisable(true);
                }
                if (txtUbicacion3.getText() != null && !txtUbicacion3.getText().trim().isEmpty()) {
                    txtUbicacion3.setDisable(false);
                    btnAdd.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnAdd2.setDisable(true);
                    btnEliminar2.setDisable(false);

                }
            }
        });
    }

    /**
     * Este método se encarga de realizar toda la lógica de autocompletado de los campos de ubicación. Funciona con
     * hilos y permite que el usuario pueda escribir sin que se ejecute la lógica de autocompletado hasta que deje
     * de escribir durante 0.5 segundos. Se recomienda no tocar este método.
     *
     * @param task: tarea que se está ejecutando
     */
    private void executeLogic(Future<?> task) {
        task = executor.submit(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ubicaciones = incidenciaService.getAllUbicaciones();
                return null;
            }
        });
    }

    /**
     * Este método se encarga de cargar los datos previos en los combobox de la vista. Se cargan los tipos de procedencia
     * y los tipos de ubicación.
     */
    private void establecerDatosPrevios() {
        cargarTipoProcedencia();
        cargarTiposUbicacion(cbTipoUbicacion);
        executeLogic(task1);

    }

    /**
     * Este método se encarga de cargar los tipos de procedencia en el combobox de la vista.
     */
    private void cargarTipoProcedencia() {
        List<Procedencia> lista = incidenciaService.getAllProcedencia();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Procedencia elemento : lista) {
            observableList.add(elemento.getTipoProcedencia());
        }
        cbProcedencia.setItems(observableList);
    }

    /**
     * Este método se encarga de cargar los tipos de ubicación en el combobox pasado por parámetros.
     *
     * @param comboBox: combobox en el que se cargarán los tipos de ubicación.
     */
    private void cargarTiposUbicacion(ComboBox<String> comboBox) {
        List<TipoLugar> listaTipoLugar = incidenciaService.getAllTipoUbicacion();
        ObservableList<String> observableListTipoUbicacion = FXCollections.observableArrayList();
        for (TipoLugar tipoLugar : listaTipoLugar) {
            observableListTipoUbicacion.add(tipoLugar.getTipoLugar());
        }
        comboBox.setItems(observableListTipoUbicacion);
    }

    /**
     * Este método se encarga de toda la lógica sobre el listener del combobox de procedencia.
     * Se encarga de deshabilitar o habilitar campos en función de la procedencia seleccionada.
     *
     * @param newValue: nuevo valor seleccionado en el combobox de procedencia.
     */
    private void procedenciaListener(String newValue) {
        Procedencia p = incidenciaService.getProcedenciaByTipoProcedencia(newValue);
        tfPrefijoNumRef.setText(p.getPrefijoProcedencia());
        txtNumExpAyto.setDisable(!(tfPrefijoNumRef.getText() == null));


        if (p.getMetodoValidacion().equals("email")) {
            System.out.println(p.getMetodoValidacion());
            txtDniCifTercero.setDisable(true);
            txtNombreTercero.setDisable(true);
            txtTelefonoTercero.setDisable(true);
            txtCorreoTercero.setDisable(false);
        } else if (p.getMetodoValidacion().equals("dni_cif")) {
            txtDniCifTercero.setDisable(false);
            txtCorreoTercero.setDisable(true);
            txtNombreTercero.setDisable(true);
            txtTelefonoTercero.setDisable(true);
        }
    }

    /**
     * Este método se encarga de toda la lógica sobre el listener del textfield de DNI/CIF.
     * Se encarga de buscar un tercero en la base de datos en función del DNI/CIF introducido.
     *
     * @param newValue: nuevo valor introducido en el textfield de DNI/CIF.
     */
    private void dniCifListener(String newValue) {
        if (comprobarFormatoDniCif()) {
            Tercero tercero = incidenciaService.getTerceroByDni(newValue);
            if (tercero != null) {
                terceroEncontrado = true;
                txtNombreTercero.setText(incidenciaService.getTerceroByDni(newValue).getNombre());
                txtCorreoTercero.setText(incidenciaService.getTerceroByDni(newValue).getEmail());
                txtTelefonoTercero.setText(incidenciaService.getTerceroByDni(newValue).getTelefono());
            } else {
                terceroEncontrado = false;
                txtCorreoTercero.setText("");
                txtTelefonoTercero.setText("");
                txtNombreTercero.setText("");
            }
            txtCorreoTercero.setDisable(false);
            txtTelefonoTercero.setDisable(false);
            txtNombreTercero.setDisable(false);
        } else {
            txtCorreoTercero.setDisable(true);
            txtTelefonoTercero.setDisable(true);
            txtNombreTercero.setDisable(true);
            txtCorreoTercero.setText("");
            txtTelefonoTercero.setText("");
            txtNombreTercero.setText("");
        }
    }

    /**
     * Este método se encarga de toda la lógica sobre el listener del textfield de correo electrónico.
     * Se encarga de buscar un tercero en la base de datos en función del correo electrónico introducido.
     *
     * @param newValue: nuevo valor introducido en el textfield de correo electrónico.
     */
    private void emailListener(String newValue) {

        if (comprobarFormatoCorreo()) {
            Tercero tercero = incidenciaService.getTerceroByEmail(newValue);
            if (tercero != null) {
                terceroEncontrado = true;
                txtNombreTercero.setText(incidenciaService.getTerceroByEmail(newValue).getNombre());
                txtDniCifTercero.setText(incidenciaService.getTerceroByEmail(newValue).getDniCif());
                txtTelefonoTercero.setText(incidenciaService.getTerceroByEmail(newValue).getTelefono());
            } else {
                terceroEncontrado = false;
                txtDniCifTercero.setText("");
                txtTelefonoTercero.setText("");
                txtNombreTercero.setText("");
            }
            txtDniCifTercero.setDisable(false);
            txtTelefonoTercero.setDisable(false);
            txtNombreTercero.setDisable(false);
        } else {
            txtDniCifTercero.setDisable(true);
            txtTelefonoTercero.setDisable(true);
            txtNombreTercero.setDisable(true);
            txtDniCifTercero.setText("");
            txtTelefonoTercero.setText("");
            txtNombreTercero.setText("");
        }
    }

    /**
     * Este método se encarga de desahibilitar o habilitar campos en función de si añade una nueva ubicación.
     * Mediante comprobaciones habilita el segundo campo o tercero.
     */
    @FXML
    private void addUbicacion() {
        if (cbTipoUbicacion2.isDisable()) {
            cbTipoUbicacion2.setDisable(false);
            btnAdd.setDisable(true);
            btnEliminar.setDisable(false);
            btnAdd2.setDisable(false);
            btnEliminar2.setDisable(true);
            cargarTiposUbicacion(cbTipoUbicacion2);
        } else if (cbTipoUbicacion3.isDisable()) {
            cbTipoUbicacion3.setDisable(false);
            btnAdd2.setDisable(true);
            btnEliminar2.setDisable(false);
            btnEliminar.setDisable(true);
            cargarTiposUbicacion(cbTipoUbicacion3);
        }
    }

    /**
     * Este método se encarga de desahibilitar o habilitar campos en función de si elimina una ubicación.
     * Mediante comprobaciones deshabilita el segundo campo o tercero.
     */
    @FXML
    private void removeUbicacion() {
        if (!cbTipoUbicacion3.isDisable()) {
            cbTipoUbicacion3.setDisable(true);
            btnAdd2.setDisable(false);
            btnEliminar2.setDisable(true);
            btnEliminar.setDisable(false);
        } else if (!cbTipoUbicacion2.isDisable()) {
            cbTipoUbicacion2.setDisable(true);
            btnAdd.setDisable(false);
            btnEliminar.setDisable(true);
            btnAdd2.setDisable(true);
            btnEliminar2.setDisable(true);

        }
    }

    /**
     * Este método se encarga de validar todos los campos de la vista y en caso de que estén correctos, llama
     * al método que se encarga de insertar la incidencia en la base de datos.
     * En caso de que haya algún error, muestra un diálogo de error con el mensaje correspondiente.
     */
    @FXML
    public void validarFormulario() {
        try {
            validarDatosIncidencia();
            validarDatosTercero();
            if (!checkbRepetida.isSelected()) {
                validarDatosUbicacion();
            }
            validarDescripcion();
            insertarIncidencia();
        } catch (InsertarIncidenciaException e) {
            mostrarDialogoError(e.getMessage());
        }
    }

    /**
     * Este método se encarga de recopilar todos los campos de la vista, crear todos los objetos necesarios
     * realizando las consultas a la base de datos con el fin de crear un objeto Incidencia completo y
     * guardardarlo en la base de datos.
     * Tras el procedimiento de guardar la Incidencia, se procede a guardar las relaciones con las ubicaciones
     * en función de los campos de ubicación que estén habilitados.
     */
    private void insertarIncidencia() {
        Incidencia nuevaIncidencia = new Incidencia();
        Procedencia p = incidenciaService.getProcedenciaByTipoProcedencia(cbProcedencia.getValue());
        System.out.println(p.getTipoProcedencia());
        nuevaIncidencia.setProcedencia(p);


        nuevaIncidencia.setNuestraReferencia(tfPrefijoNumRef.getText() + tfNumReferencia.getText());

        if (txtNumRegAyto.getText() != null && !txtNumRegAyto.getText().trim().isEmpty()) {
            nuevaIncidencia.setNumRegistroAyuntamiento(txtNumRegAyto.getText());
        }
        if (cbProcedencia.equals("GESTIONA")) {
            nuevaIncidencia.setNumExpedienteAyuntamiento(txtNumExpAyto.getText());
        }

        Tercero tercero = new Tercero();
        tercero.setDniCif(txtDniCifTercero.getText());
        tercero.setEmail(txtCorreoTercero.getText());
        tercero.setTelefono(txtTelefonoTercero.getText());
        tercero.setNombre(txtNombreTercero.getText());

        if (!terceroEncontrado) {
            incidenciaService.saveTercero(tercero);
            nuevaIncidencia.setTercero(tercero);
            System.out.println("Tercero no encontrado");
        } else {
            System.out.println("Tercero encontrado");
            Procedencia procedencia = incidenciaService.getProcedenciaByTipoProcedencia(cbProcedencia.getValue().trim());
            if (procedencia.getMetodoValidacion().equals("dni_cif")) {
                Tercero terceroEncontrado = incidenciaService.getTerceroByDni(txtDniCifTercero.getText());
                System.out.println("Tercero encontrado");
                terceroEncontrado.setNombre(txtNombreTercero.getText());
                terceroEncontrado.setTelefono(txtTelefonoTercero.getText());
                terceroEncontrado.setEmail(txtCorreoTercero.getText());
                incidenciaService.updateTerceroByDni(txtDniCifTercero.getText(), terceroEncontrado);
                nuevaIncidencia.setTercero(terceroEncontrado);
            } else if (procedencia.getMetodoValidacion().equals("email")) {
                System.out.println("Tercero encontrado");
                Tercero terceroEncontrado = incidenciaService.getTerceroByEmail(txtCorreoTercero.getText());
                terceroEncontrado.setNombre(txtNombreTercero.getText());
                terceroEncontrado.setTelefono(txtTelefonoTercero.getText());
                terceroEncontrado.setDniCif(txtDniCifTercero.getText());
                incidenciaService.updateTerceroByEmail(terceroEncontrado);
                nuevaIncidencia.setTercero(terceroEncontrado);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Formatea la fecha de notificación
        LocalDate valorFechaNotificacion = dpFechaNotificacion.getValue();
        String fechaNotificacionFormateada = valorFechaNotificacion.format(formatter);
        java.sql.Date fechaNotificacion = java.sql.Date.valueOf(LocalDate.parse(fechaNotificacionFormateada, formatter));

        // Formatea la fecha de servicios generales
        LocalDate valorFechaServiciosGenerales = dpFechaServGen.getValue();
        String fechaServiciosGeneralesFormateada = valorFechaServiciosGenerales.format(formatter);
        java.sql.Date fechaServiciosGenerales = java.sql.Date.valueOf(LocalDate.parse(fechaServiciosGeneralesFormateada, formatter));

        // Establece las fechas formateadas en tu objeto nuevaIncidencia
        nuevaIncidencia.setFechaNotificacion(fechaNotificacion);
        nuevaIncidencia.setFechaServiciosGenerales(fechaServiciosGenerales);

        nuevaIncidencia.setDescripcionIncidencia(txtDescripcion.getText());

        nuevaIncidencia.setUsuario(usuarioActual);

        nuevaIncidencia.setEstado(incidenciaService.getEstadoByNombre("PRESENTADA"));

        if (checkbRepetida.isSelected()) {
            nuevaIncidencia.setIncidenciaOrigen(Integer.parseInt(txtIncidenciaOrigen.getText()));
        }

        incidenciaService.saveIncidencia(nuevaIncidencia);

        if (!checkbRepetida.isSelected()){
            List<Ubicacion> ubicaciones = new ArrayList<>();

            ubicaciones.add(incidenciaService.getUbicacionByTipoLugarAndNombreLugar(cbTipoUbicacion.getValue().trim(), txtUbicacion1.getText()));
            if (!cbTipoUbicacion2.isDisable()) {
                ubicaciones.add(incidenciaService.getUbicacionByTipoLugarAndNombreLugar(cbTipoUbicacion2.getValue().trim(), txtUbicacion2.getText()));
            }
            if (!cbTipoUbicacion3.isDisable()) {
                ubicaciones.add(incidenciaService.getUbicacionByTipoLugarAndNombreLugar(cbTipoUbicacion3.getValue().trim(), txtUbicacion3.getText()));
            }
            for (Ubicacion u : ubicaciones) {
                UbicacionIncidencia ubicacionIncidencia = new UbicacionIncidencia();
                ubicacionIncidencia.setIncidencia(nuevaIncidencia);
                ubicacionIncidencia.setUbicacion(u);
                incidenciaService.saveUbicacionIncidencia(ubicacionIncidencia);
            }
        }


    }

    /**
     * Este método se encarga de validar todos los datos que se encuentran en el desplegable relacionado con
     * los datos principales de la incidencia, comprobando que no estén vacíos, no sean nulos y en caso de que
     * tengan un formato específico, que cumplan con dicho formato.
     *
     * @throws InsertarIncidenciaException: excepción que se lanza en caso de que haya algún error en los campos.
     */
    private void validarDatosIncidencia() throws InsertarIncidenciaException {
        if (cbProcedencia.getValue() == null || cbProcedencia.getValue().trim().isEmpty()) {
            throw new InsertarIncidenciaException("Debes de seleccionar un medio de procedencia");
        }
        if (tfPrefijoNumRef.getText() == null || tfPrefijoNumRef.getText().trim().isEmpty()
                || tfNumReferencia.getText() == null || tfNumReferencia.getText().trim().isEmpty()) {
            throw new InsertarIncidenciaException("Debes proporcionar un número de referencia");
        }
        if (cbProcedencia.getValue().equals("GESTIONA")) {
            if (txtNumRegAyto.getText() == null || txtNumRegAyto.getText().trim().isEmpty()
                    || txtNumExpAyto.getText() == null || txtNumExpAyto.getText().trim().isEmpty()) {
                throw new InsertarIncidenciaException("Debes proporcionar un número de registro y expediente");
            }
        }
        if (dpFechaNotificacion.getValue() == null) {
            throw new InsertarIncidenciaException("Debes proporcionar una fecha de notificación");
        }
        if (dpFechaServGen.getValue() == null) {
            throw new InsertarIncidenciaException("Debes proporcionar una fecha de recepción de servicios generales");
        }
        if (dpFechaNotificacion.getValue().isAfter(dpFechaServGen.getValue())) {
            throw new InsertarIncidenciaException("La fecha de notificación no puede ser posterior a la de recepción de servicios generales");
        }
    }

    /**
     * Este método se encarga de validar todos los datos que se encuentran en el desplegable relacionado con
     * los datos del tercero, comprobando que no estén vacíos, no sean nulos y en caso de que tengan un formato
     * específico, que cumplan con dicho formato.
     *
     * @throws InsertarIncidenciaException: excepción que se lanza en caso de que haya algún error en los campos.
     */
    private void validarDatosTercero() throws InsertarIncidenciaException {

        boolean campoRelleno = false;

        if (txtDniCifTercero.getText() != null && !txtDniCifTercero.getText().trim().isEmpty()) {
            campoRelleno = true;
        }
        if (txtCorreoTercero.getText() != null && !txtCorreoTercero.getText().trim().isEmpty()) {
            campoRelleno = true;
        }
        if (txtTelefonoTercero.getText() != null && !txtTelefonoTercero.getText().trim().isEmpty()) {
            campoRelleno = true;
        }
        if (txtNombreTercero.getText() != null && !txtNombreTercero.getText().trim().isEmpty()) {
            campoRelleno = true;
        }

        if (!comprobarFormatoCorreo() && !campoRelleno) {
            throw new InsertarIncidenciaException("El correo debe tener un formato válido");
        }

        if (!txtTelefonoTercero.getText().matches("\\d{9}") && !campoRelleno) {
            throw new InsertarIncidenciaException("El teléfono debe tener un formato válido");
        }

        if (!comprobarFormatoDniCif() && !campoRelleno) {
            throw new InsertarIncidenciaException("El DNI/CIF debe tener un formato válido");
        }

        if (campoRelleno == false) {
            throw new InsertarIncidenciaException("Debes proporcionar al menos un dato del tercero");
        }
    }

    /**
     * Este método se encarga de validar todos los datos que se encuentran en los campos de ubicación, comprobando
     * que no estén vacíos, no sean nulos y en caso de que tengan un formato específico, que cumplan con dicho formato.
     * También se encarga de comprobar que se haya seleccionado al menos un tipo de ubicación, y no se comprueban los
     * demás si estan desahibiltados.
     *
     * @throws InsertarIncidenciaException: excepción que se lanza en caso de que haya algún error en los campos.
     */
    private void validarDatosUbicacion() throws InsertarIncidenciaException {
        if (cbTipoUbicacion.getValue() == null || cbTipoUbicacion.getValue().trim().isEmpty()) {
            throw new InsertarIncidenciaException("Debes seleccionar al menos un tipo de ubicación");
        }
        if (txtUbicacion1.getText() == null || txtUbicacion1.getText().trim().isEmpty()) {
            throw new InsertarIncidenciaException("Debes proporcionar al menos una ubicación");
        }
        if (!cbTipoUbicacion2.isDisable()) {
            if (cbTipoUbicacion2.getValue() == null || cbTipoUbicacion2.getValue().trim().isEmpty()) {
                throw new InsertarIncidenciaException("Debes seleccionar al menos un tipo de ubicación para la segunda localización");
            }
            if (txtUbicacion2.getText() == null || txtUbicacion2.getText().trim().isEmpty()) {
                throw new InsertarIncidenciaException("Debes proporcionar al menos una ubicación para la segunda localización");
            }
        }
        if (!cbTipoUbicacion3.isDisable()) {
            if (cbTipoUbicacion3.getValue() == null || cbTipoUbicacion3.getValue().trim().isEmpty()) {
                throw new InsertarIncidenciaException("Debes seleccionar al menos un tipo de ubicación para la tercero localización");
            }
            if (txtUbicacion3.getText() == null || txtUbicacion3.getText().trim().isEmpty()) {
                throw new InsertarIncidenciaException("Debes proporcionar al menos una ubicación para la tercero localización");
            }
        }

    }

    /**
     * Este método se encarga de validar el campo de descripción de la incidencia, comprobando que no esté vacío
     * y que no supere los 250 caracteres.
     *
     * @throws InsertarIncidenciaException: excepción que se lanza en caso de que haya algún error en los campos.
     */
    private void validarDescripcion() throws InsertarIncidenciaException {
        if (txtDescripcion.getText() == null || txtDescripcion.getText().trim().isEmpty()) {
            throw new InsertarIncidenciaException("Debes proporcionar una descripción de la incidencia");
        }
        if (txtDescripcion.getText().length() > 250) {
            throw new InsertarIncidenciaException("La descripción no puede superar los 250 caracteres");
        }
    }

    /**
     * Este método se encarga de comprobar que el formato del correo electrónico sea correcto.
     *
     * @return true si el formato es correcto, false en caso contrario.
     */
    private boolean comprobarFormatoCorreo() {
        String correo = txtCorreoTercero.getText();
        String regex = "^[\\w-]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]+)$";

        if ((correo != null && !correo.trim().isEmpty()) && correo.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este método se encarga de comprobar que el formato del DNI/CIF sea correcto.
     *
     * @return true si el formato es correcto, false en caso contrario.
     */
    private boolean comprobarFormatoDniCif() {
        String dniCif = txtDniCifTercero.getText().toUpperCase();
        String regexDni = "\\d{8}[A-HJ-NP-TV-Z]";
        String regexCif = "[ABCDEFGHJNPQRSUVW]{1}\\d{7}[0-9,A-J]";

        if ((dniCif == null || dniCif.trim().isEmpty()) || dniCif.matches(regexDni) || dniCif.matches(regexCif)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este método se encarga de mostrar una alerta de confirmación para comprobar si la incidencia que se va a enviar
     * está duplicada previamente a comenzar a introducir los datos.
     *
     * @return true si se confirma que no está duplicada, false en caso contrario.
     */
    private boolean alertaIncidenciaDuplicada() {
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

    /**
     * Este método se encarga de mostrar un diálogo de error con el título y mensaje pasados por parámetros.
     *
     * @param mensaje : mensaje del diálogo de error.
     */
    private void mostrarDialogoError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error en los campos del formulario");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
