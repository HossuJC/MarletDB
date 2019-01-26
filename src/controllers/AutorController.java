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
import models.Autor;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class AutorController implements Initializable {

    @FXML
    private TableView<Autor> tblData;
    @FXML
    private Button btnNuevoRegistro;
    private ObservableList<Autor> data;
    @FXML
    private Button btnRecargar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarData();
    }

    private void cargarData() {
        try {
            String sql = "Select * from autor";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            data = FXCollections.observableArrayList(dataBaseArrayList(rs));
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                TableColumn column = new TableColumn<>();
                switch (rs.getMetaData().getColumnName(i + 1)) {
                    case "cedula":
                        column.setText("Cédula");
                        break;
                    case "nombres":
                        column.setText("Nombres");
                        break;
                    case "apellidos":
                        column.setText("Apellidos");
                        break;
                    case "nacionalidad":
                        column.setText("Nacionalidad");
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
            Logger.getLogger(EditorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void nuevoRegistro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AutorNuevo.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Ingreso autor");
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
