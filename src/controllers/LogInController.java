/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.ConnectionMySQL;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuario;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class LogInController implements Initializable {

    @FXML
    private TextField txfUsuario;
    @FXML
    private PasswordField txfContrasena;
    @FXML
    private Button btnIngresar;
    public static Connection con = null;
    public static Usuario usuarioActivo = new Usuario();
    
    @FXML
    private void ingresarUsuario(ActionEvent event) throws IOException {
        String usuario = txfUsuario.getText();
        String contrasena = txfContrasena.getText();
        con = ConnectionMySQL.conexionDB(usuario, contrasena);
        String sql1 = "use marletdb";
        String sql2 = "select * from usuario where cedula = '" + usuario + "'";
        try {
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.execute();
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ResultSet resultSet = ps2.executeQuery(sql2);
            if (!resultSet.next()) {
                mostrarDialogo("Usuario y/o Contraseña no válidos\nVuelva a intentarlo", "Autenticación fallida", "Error");
            } else {
                usuarioActivo.setCedula(resultSet.getString("cedula"));
                usuarioActivo.setApellidos(resultSet.getString("apellidos"));
                usuarioActivo.setEmail(resultSet.getString("email"));
                usuarioActivo.setNombres(resultSet.getString("nombres"));
                usuarioActivo.setTipo(resultSet.getString("tipo"));
		Node nodo = (Node) event.getSource();
                Stage stage = (Stage) nodo.getScene().getWindow();
                stage.setMinWidth(1025);
                stage.setMinHeight(750);
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"))));
                } catch (IOException ex) {
                    Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public static void mostrarDialogo(String info, String cabecera, String titulo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(info);
        alert.setHeaderText(cabecera);
        alert.setTitle(titulo);
        alert.showAndWait();
    }
    
}
