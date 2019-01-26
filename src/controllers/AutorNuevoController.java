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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class AutorNuevoController implements Initializable {

    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardarRegistro(ActionEvent event) {
        try {
            String sql = "{call nuevo_registro_autor(?,?,?,?)}";
            CallableStatement stmt = con.prepareCall(sql);
            stmt.setString(1, (!txtCedula.getText().equals("")) ? txtCedula.getText() : null);
            stmt.setString(2, (!txtNombres.getText().equals("")) ? txtNombres.getText() : null);
            stmt.setString(3, (!txtApellidos.getText().equals("")) ? txtApellidos.getText() : null);
            stmt.setString(4, (!txtNacionalidad.getText().equals("")) ? txtNacionalidad.getText() : null);
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
