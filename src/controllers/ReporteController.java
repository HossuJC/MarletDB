/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.LogInController.con;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import models.Editorial;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class ReporteController implements Initializable {

    @FXML
    private TableView tblData;
    @FXML
    private ComboBox<String> cbReporte;
    @FXML
    private Button btnReporte;
    private ObservableList<String> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String sql = "SHOW full TABLES where Table_type='VIEW'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            data = FXCollections.observableArrayList(dataBaseArrayList(rs));
            cbReporte.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<String> dataBaseArrayList(ResultSet rs) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        while (rs.next()) {
            String s = rs.getString(1);
            array.add(s);
        }
        return array;
    }

    @FXML
    private void verReporte(ActionEvent event) {
        tblData.getItems().clear();
        tblData.getColumns().clear();
        ObservableList dataV = FXCollections.observableArrayList();
        try {
            String sqlv = "select * from " + cbReporte.getValue();
            ResultSet rs = con.createStatement().executeQuery(sqlv);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tblData.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                dataV.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}