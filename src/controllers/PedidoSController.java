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
import models.PedidoCliente;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class PedidoSController implements Initializable {

    @FXML
    private TableView<PedidoCliente> tblData;
    @FXML
    private Button btnNuevoRegistro;
    private ObservableList<PedidoCliente> data;
    @FXML
    private Button btnRecargar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarData();
    }
    
    private void cargarData() {
        try {
            String sql = "Select * from pedido_cliente";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            data = FXCollections.observableArrayList(dataBaseArrayList(rs));
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                TableColumn column = new TableColumn<>();
                switch (rs.getMetaData().getColumnName(i + 1)) {
                    case "id_pedido_cliente":
                        column.setText("Identificador");
                        break;
                    case "fecha_orden":
                        column.setText("Fecha de creación");
                        break;
                    case "concepto":
                        column.setText("Concepto");
                        break;
                    case "forma_pago":
                        column.setText("Forma de pago");
                        break;
                    case "fecha_envio":
                        column.setText("Fecha de envío");
                        break;
                    case "ruc_cliente":
                        column.setText("Ruc del cliente");
                        break;
                    case "ruc_trans":
                        column.setText("Ruc del transportista");
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

    private ArrayList<PedidoCliente> dataBaseArrayList(ResultSet rs) throws SQLException {
        ArrayList<PedidoCliente> array = new ArrayList<>();
        while (rs.next()) {
            PedidoCliente pc = new PedidoCliente();
            pc.setIdPedidoCliente(rs.getInt("id_pedido_cliente"));
            pc.setFechaOrden(rs.getDate("fecha_orden"));
            pc.setConcepto(rs.getString("concepto"));
            pc.setFormaPago(rs.getString("forma_pago"));
            pc.setFechaEnvio(rs.getDate("fecha_envio"));
            pc.setRucTrans(rs.getString("ruc_trans"));
            pc.setRucCliente(rs.getString("ruc_cliente"));
            array.add(pc);
        }
        return array;
    }

    @FXML
    private void nuevoRegistro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PedidoSNuevo.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Ingreso pedido saliente");
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
