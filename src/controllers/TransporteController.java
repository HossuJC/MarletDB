/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.LogInController.con;
import static controllers.LogInController.usuarioActivo;
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
import models.Transportista;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class TransporteController implements Initializable {

    @FXML
    private TableView<Transportista> tblData;
    @FXML
    private Button btnNuevoRegistro;
    private ObservableList<Transportista> data;
    @FXML
    private Button btnRecargar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (usuarioActivo.getTipo().equals("Trabajador")) {
            btnNuevoRegistro.setDisable(true);
        }
        cargarData();
    }

    private void cargarData() {
        try {
            String sql = "Select * from transportista";
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
                    default:
                        column.setText(rs.getMetaData().getColumnName(i + 1));
                        break;
                }
                column.setCellValueFactory(new PropertyValueFactory<>(rs.getMetaData().getColumnName(i + 1)));
                tblData.getColumns().add(column);
            }
            tblData.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(TransporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<Transportista> dataBaseArrayList(ResultSet rs) throws SQLException {
        ArrayList<Transportista> array = new ArrayList<>();
        while (rs.next()) {
            Transportista t = new Transportista();
            t.setRuc(rs.getString("ruc"));
            t.setRazonSocial(rs.getString("razon_social"));
            t.setCiudad(rs.getString("ciudad"));
            t.setDireccion(rs.getString("direccion"));
            t.setTelefono(rs.getString("telefono"));
            t.setEmail(rs.getString("email"));
            array.add(t);
        }
        return array;
    }

    @FXML
    private void nuevoRegistro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/TransporteNuevo.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Ingreso transportista");
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
