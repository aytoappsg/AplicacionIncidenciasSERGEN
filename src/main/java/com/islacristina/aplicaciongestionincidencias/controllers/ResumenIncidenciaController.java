package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Incidencia;
import com.islacristina.aplicaciongestionincidencias.model.UbicacionIncidencia;
import com.islacristina.aplicaciongestionincidencias.services.ResumenIncidenciaService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

import static com.islacristina.aplicaciongestionincidencias.AplicacionGestionIncidenciasApplication.applicationContext;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Controller
public class ResumenIncidenciaController implements Initializable {
    @Autowired
    private DashboardController dashboardController;

    private Incidencia incidencia;

    private  ResumenIncidenciaService resumenIncidenciaService;


    @FXML
    private StackPane stackPane;

    @FXML
    private Label numOrden;

    @FXML
    private TextField numReferencia;

    @FXML
    private DatePicker fechaNotificacion;

    @FXML
    private DatePicker fechaServiciosGenerales;

   @FXML
   private ComboBox tipo;
    @FXML
    private ComboBox tipo2;
    @FXML
    private ComboBox tipo3;

   @FXML
    private ComboBox aplicadoA;
    @FXML
    private ComboBox aplicadoA2;
    @FXML
    private ComboBox aplicadoA3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //System.out.println(dashboardController.getIncidencia().getNumOrden());
    }

    @Autowired
    public ResumenIncidenciaController(DashboardController dashboardController, ResumenIncidenciaService resumenIncidenciaService) {
        this.dashboardController = dashboardController;
        this.resumenIncidenciaService = resumenIncidenciaService;
    }


    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
        numOrden.setText(String.valueOf(incidencia.getNumOrden()));
        numReferencia.setText(incidencia.getNuestraReferencia());

        // Convierte java.util.Date a java.time.LocalDate
        java.util.Date fechaNotificacionDate = incidencia.getFechaNotificacion();
        if (fechaNotificacionDate != null) {
            LocalDate fechaNotificacionLocalDate = fechaNotificacionDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            fechaNotificacion.setValue(fechaNotificacionLocalDate);
        }

        java.util.Date fechaSeriviciosGeneralesDate = incidencia.getFechaNotificacion();
        if (fechaNotificacionDate != null) {
            LocalDate fechaNotificacionLocalDate = fechaSeriviciosGeneralesDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            fechaServiciosGenerales.setValue(fechaNotificacionLocalDate);
        }

        if (incidencia.getIncidenciaOrigen() == null) {
            List<UbicacionIncidencia> listaUbicacionesIncidencia = resumenIncidenciaService.getAllUbicacionIncidenciaByInciencia(incidencia.getNumOrden());
            if (listaUbicacionesIncidencia.size() > 0) {
                tipo.setValue(listaUbicacionesIncidencia.get(0).getUbicacion().getTipoLugar().getTipoLugar());
                aplicadoA.setValue(listaUbicacionesIncidencia.get(0).getUbicacion().getLugar().getNombreLugar());
            }
            if (listaUbicacionesIncidencia.size() > 1) {
                tipo2.setValue(listaUbicacionesIncidencia.get(1).getUbicacion().getTipoLugar().getIdTipoLugar());
                aplicadoA2.setValue(listaUbicacionesIncidencia.get(1).getUbicacion().getLugar().getNombreLugar());
            }
            if (listaUbicacionesIncidencia.size() > 2) {
                tipo3.setValue(listaUbicacionesIncidencia.get(2).getUbicacion().getTipoLugar().getIdTipoLugar());
                aplicadoA3.setValue(listaUbicacionesIncidencia.get(2).getUbicacion().getLugar().getNombreLugar());
            }


            }
        }

        private void loadProcede(){
            FXMLLoader procedeLoader = new FXMLLoader(getClass().getResource("/procedeIncidencia.fxml"));
            procedeLoader.setControllerFactory(applicationContext::getBean);
        }

    }


