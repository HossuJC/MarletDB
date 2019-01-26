/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.LogInController.con;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class ClienteNuevoController implements Initializable {

    @FXML
    private TextField txtRuc;
    @FXML
    private TextField txtRazonSocial;
    @FXML
    private ComboBox<String> cbTipo;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDescuento;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> tipos = FXCollections.observableArrayList();
        tipos.add("Institución");
        tipos.add("Persona natural");
        cbTipo.setItems(tipos);
    }

    @FXML
    private void guardarRegistro(ActionEvent event) {
        try {
            String sql = "{call nuevo_registro_cliente(?,?,?,?,?,?,?,?)}";
            CallableStatement stmt = con.prepareCall(sql);
            stmt.setString(1, (!txtRuc.getText().equals("")) ? txtRuc.getText() : null);
            stmt.setString(2, (!txtRazonSocial.getText().equals("")) ? txtRazonSocial.getText() : null);
            stmt.setString(3, cbTipo.getValue());
            stmt.setString(4, (!txtCiudad.getText().equals("")) ? txtCiudad.getText() : null);
            stmt.setString(5, (!txtDireccion.getText().equals("")) ? txtDireccion.getText() : null);
            stmt.setString(6, (!txtTelefono.getText().equals("")) ? txtTelefono.getText() : null);
            stmt.setString(7, (!txtEmail.getText().equals("")) ? txtEmail.getText() : null);
            stmt.setFloat(8, (!txtDescuento.getText().equals("")) ? Float.valueOf(txtDescuento.getText()) : null);
            stmt.execute();
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditorialNuevoController.class.getName()).log(Level.SEVERE, null, ex);
            LogInController.mostrarDialogo("Verifique que los datos sean correctos y vuelva a intentarlo", "Error al ingresar los datos", "Error");
        }
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

}
