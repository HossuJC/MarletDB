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
import models.Libro;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class LibroController implements Initializable {

    @FXML
    private TableView<Libro> tblData;
    @FXML
    private Button btnNuevoRegistro;
    private ObservableList<Libro> data;
    @FXML
    private Button btnRecargar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarData();
    }
    
    private void cargarData() {
        try {
            String sql = "Select * from libro";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            data = FXCollections.observableArrayList(dataBaseArrayList(rs));
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                TableColumn column = new TableColumn<>();
                switch (rs.getMetaData().getColumnName(i + 1)) {
                    case "isbn":
                        column.setText("ISBN");
                        break;
                    case "titulo":
                        column.setText("Título");
                        break;
                    case "asignatura":
                        column.setText("Asignatura");
                        break;
                    case "curso":
                        column.setText("Curso");
                        break;
                    case "edicion":
                        column.setText("Edición");
                        break;
                    case "lugar_edicion":
                        column.setText("Lugar de edición");
                        break;
                    case "anio_edicion":
                        column.setText("Año de edición");
                        break;
                    case "precio":
                        column.setText("Precio");
                        break;
                    case "stock":
                        column.setText("Stock actual");
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
            Logger.getLogger(LibroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<Libro> dataBaseArrayList(ResultSet rs) throws SQLException {
        ArrayList<Libro> array = new ArrayList<>();
        while (rs.next()) {
            Libro l = new Libro();
            l.setIsbn(rs.getString("isbn"));
            l.setTitulo(rs.getString("titulo"));
            l.setAsignatura(rs.getString("asignatura"));
            l.setCurso(rs.getInt("curso"));
            l.setEdicion(rs.getInt("edicion"));
            l.setLugarEdicion(rs.getString("lugar_edicion"));
            l.setAnioEdicion(rs.getInt("anio_edicion"));
            l.setPrecio(rs.getFloat("precio"));
            l.setStock(rs.getInt("stock"));
            array.add(l);
        }
        return array;
    }

    @FXML
    private void nuevoRegistro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibroNuevo.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Ingreso libro");
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
