/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.LogInController.con;
import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Cliente;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class ClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tblData;
    @FXML
    private Button btnNuevoRegistro;
    private ObservableList<Cliente> data;
    @FXML
    private Button btnRecargar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarData();
    }

    private void cargarData() {
        try {
            String sql = "Select * from cliente";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            data = FXCollections.observableArrayList(dataBaseArrayList(rs));
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                TableColumn column = new TableColumn<>();
                switch (rs.getMetaData().getColumnName(i + 1)) {
                    case "ruc":
                        column.setText("Ruc");
                        break;
                    case "razon_social":
                        column.setText("Razón Social");
                        break;
                    case "tipo":
                        column.setText("Tipo de cliente");
                        break;
                    case "ciudad":
                        column.setText("Ciudad");
                        break;
                    case "direccion":
                        column.setText("Dirección");
                        break;
                    case "telefono":
                        column.setText("Teléfono");
                        break;
                    case "email":
                        column.setText("E-mail");
                        break;
                    case "descuento":
                        column.setText("Descuento");
                        break;
                    default:
                        column.setText(rs.getMetaData().getColumnName(i + 1));
                        break;
                }
                column.setCellValueFactory(new PropertyValueFactory<>(rs.getMetaData().getColumnName(i + 1)));
                tblData.getColumns().add(column);
            }
            tblData.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<Cliente> dataBaseArrayList(ResultSet rs) throws SQLException {
        ArrayList<Cliente> array = new ArrayList<>();
        while (rs.next()) {
            Cliente c = new Cliente();
            c.setRuc(rs.getString("ruc"));
            c.setRazonSocial(rs.getString("razon_social"));
            c.setTipo(rs.getString("tipo"));
            c.setCiudad(rs.getString("ciudad"));
            c.setDireccion(rs.getString("direccion"));
            c.setTelefono(rs.getString("telefono"));
            c.setEmail(rs.getString("email"));
            c.setDescuento(rs.getFloat("descuento"));
            array.add(c);
        }
        return array;
    }

    @FXML
    private void nuevoRegistro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ClienteNuevo.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Ingreso cliente");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EditorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void recargar(ActionEvent event) {
        tblData.getItems().clear();
        tblData.getColumns().clear();
        cargarData();
    }

}
