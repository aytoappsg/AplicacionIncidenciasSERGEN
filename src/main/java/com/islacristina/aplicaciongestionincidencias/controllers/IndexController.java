package com.islacristina.aplicaciongestionincidencias.controllers;

import com.islacristina.aplicaciongestionincidencias.model.Cliente;
import com.islacristina.aplicaciongestionincidencias.repo.ClienteRepo;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    @Autowired
    @Qualifier("lblTitulo")
    private String titulo;

    @Autowired
    private ClienteRepo clienteRepo;

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<Cliente> comboCliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTitulo.setText(titulo);

        comboCliente.setItems(FXCollections.observableArrayList(clienteRepo.findAll()));
    }

    @FXML
    public void onSave(){
        Cliente cliente = new Cliente();

        cliente.setNombre(txtNombre.getText());
        cliente.setApellido(txtApellido.getText());
        cliente.setTelefono(txtTelefono.getText());

        clienteRepo.save(cliente);
        comboCliente.setItems(FXCollections.observableArrayList(clienteRepo.findAll()));
    }
}
