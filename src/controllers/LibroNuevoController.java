/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.LogInController.con;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import models.Autor;

/**
 * FXML Controller class
 *
 * @author Josué Cabezas
 */
public class LibroNuevoController implements Initializable {

    @FXML
    private TextField txtIsbn;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAsignatura;
    @FXML
    private TextField txtCurso;
    @FXML
    private TextField txtEdicion;
    @FXML
    private TextField txtLugar;
    @FXML
    private TextField txtAnio;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtStock;
    @FXML
    private ComboBox<Autor> cbAutor1;
    @FXML
    private ComboBox<Autor> cbAutor2;
    @FXML
    private ComboBox<Autor> cbAutor3;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Autor> data = FXCollections.observableArrayList();
        try {
            String sql = "Select * from autor";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            data = FXCollections.observableArrayList(dataBaseArrayList(rs));
        } catch (SQLException ex) {
            Logger.getLogger(EditorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbAutor1.setItems(data);
        cbAutor2.setItems(data);
        cbAutor3.setItems(data);
    }

    private ArrayList<Autor> dataBaseArrayList(ResultSet rs) throws SQLException {
        ArrayList<Autor> array = new ArrayList<>();
        while (rs.next()) {
            Autor a = new Autor();
            a.setCedula(rs.getString("cedula"));
            a.setNombres(rs.getString("nombres"));
            a.setApellidos(rs.getString("apellidos"));
            a.setNacionalidad(rs.getString("nacionalidad"));
            array.add(a);
        }
        return array;
    }

    @FXML
    private void guardarRegistro(ActionEvent event) {
        try {
            String sql = "{call nuevo_registro_libro(?,?,?,?,?,?,?,?,?)}";
            CallableStatement stmt = con.prepareCall(sql);
            stmt.setString(1, (!txtIsbn.getText().equals("")) ? txtIsbn.getText() : null);
            stmt.setString(2, (!txtTitulo.getText().equals("")) ? txtTitulo.getText() : null);
            stmt.setString(3, (!txtAsignatura.getText().equals("")) ? txtAsignatura.getText() : null);
            stmt.setInt(4, (!txtCurso.getText().equals("")) ? Integer.valueOf(txtCurso.getText()) : null);
            stmt.setInt(5, (!txtEdicion.getText().equals("")) ? Integer.valueOf(txtEdicion.getText()) : null);
            stmt.setString(6, (!txtLugar.getText().equals("")) ? txtLugar.getText() : null);
            stmt.setInt(7, (!txtAnio.getText().equals("")) ? Integer.valueOf(txtAnio.getText()) : null);
            stmt.setFloat(8, (!txtPrecio.getText().equals("")) ? Float.valueOf(txtPrecio.getText()) : null);
            stmt.setInt(9, (!txtStock.getText().equals("")) ? Integer.valueOf(txtStock.getText()) : null);
            String sql1 = "{call nuevo_registro_autor_libro(?,?)}";
            CallableStatement stmt1 = con.prepareCall(sql1);
            stmt1.setString(1, txtIsbn.getText());
            stmt1.setString(2, cbAutor1.getValue().getCedula());
            if (cbAutor2.getValue() != null) {
                String sql2 = "{call nuevo_registro_autor_libro(?,?)}";
                CallableStatement stmt2 = con.prepareCall(sql2);
                stmt2.setString(1, txtIsbn.getText());
                stmt2.setString(2, cbAutor2.getValue().getCedula());
            }
            if (cbAutor3.getValue() != null) {
                String sql3 = "{call nuevo_registro_autor_libro(?,?)}";
                CallableStatement stmt3 = con.prepareCall(sql3);
                stmt3.setString(1, txtIsbn.getText());
                stmt3.setString(2, cbAutor3.getValue().getCedula());
            }
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
