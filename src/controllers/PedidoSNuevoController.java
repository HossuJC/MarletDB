/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.LogInController.con;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Cliente;
import models.Libro;
import models.Transportista;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class PedidoSNuevoController implements Initializable {

    @FXML
    private TextField txtComentario;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ComboBox<Cliente> cbCliente;
    @FXML
    private ComboBox<Transportista> cbTrans;
    @FXML
    private ComboBox<String> cbFormaPago;
    @FXML
    private ComboBox<Libro> cbLibro1;
    @FXML
    private TextField txtLibro1;
    @FXML
    private ComboBox<Libro> cbLibro2;
    @FXML
    private TextField txtLibro2;
    @FXML
    private ComboBox<Libro> cbLibro3;
    @FXML
    private TextField txtLibro3;
    @FXML
    private ComboBox<Libro> cbLibro4;
    @FXML
    private TextField txtLibro4;
    @FXML
    private ComboBox<Libro> cbLibro5;
    @FXML
    private TextField txtLibro5;
    @FXML
    private ComboBox<Libro> cbLibro6;
    @FXML
    private TextField txtLibro6;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> formaPago = FXCollections.observableArrayList();
        formaPago.add("cheque");
        formaPago.add("efectivo");
        cbFormaPago.setItems(formaPago);
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        try {
            String sqlc = "Select * from cliente";
            PreparedStatement psc = con.prepareStatement(sqlc);
            ResultSet rsc = psc.executeQuery(sqlc);
            clientes = FXCollections.observableArrayList(clientesArrayList(rsc));
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbCliente.setItems(clientes);
        
        ObservableList<Transportista> trans = FXCollections.observableArrayList();
        try {
            String sqlt = "Select * from transportista";
            PreparedStatement pst = con.prepareStatement(sqlt);
            ResultSet rst = pst.executeQuery(sqlt);
            trans = FXCollections.observableArrayList(transArrayList(rst));
        } catch (SQLException ex) {
            Logger.getLogger(TransporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbTrans.setItems(trans);
        
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        try {
            String sqll = "Select * from libro";
            PreparedStatement psl = con.prepareStatement(sqll);
            ResultSet rsl = psl.executeQuery(sqll);
            libros = FXCollections.observableArrayList(librosArrayList(rsl));
        } catch (SQLException ex) {
            Logger.getLogger(LibroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbLibro1.setItems(libros);
        cbLibro2.setItems(libros);
        cbLibro3.setItems(libros);
        cbLibro4.setItems(libros);
        cbLibro5.setItems(libros);
        cbLibro6.setItems(libros);
    }

    private ArrayList<Cliente> clientesArrayList(ResultSet rs) throws SQLException {
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
    
    private ArrayList<Transportista> transArrayList(ResultSet rs) throws SQLException {
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
    
    private ArrayList<Libro> librosArrayList(ResultSet rs) throws SQLException {
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
    private void guardarRegistro(ActionEvent event) {
        try {
            String sql = "{call nuevo_registro_pedido_cliente(?,?,?,?,?)}";
            CallableStatement stmt = con.prepareCall(sql);
            stmt.setString(1, (!txtComentario.getText().equals("")) ? txtComentario.getText() : null);
            stmt.setString(2, cbFormaPago.getValue());
            stmt.setDate(3, (dpFecha.getValue() != null) ? Date.valueOf(dpFecha.getValue().toString()) : null);
            stmt.setString(4, (cbCliente.getValue() != null) ? cbCliente.getValue().getRuc() : null);
            stmt.setString(5, (cbTrans.getValue() != null) ? cbTrans.getValue().getRuc() : null);
            stmt.execute();
            if (cbLibro1.getValue() != null && !txtLibro1.getText().equals("")) {
                String sql1 = "{call nuevo_registro_set_cliente(?, ?)}";
                CallableStatement stmt1 = con.prepareCall(sql1);
                stmt1.setString(1, cbLibro1.getValue().getIsbn());
                stmt1.setInt(2, Integer.parseInt(txtLibro1.getText()));
                stmt1.execute();
            }
            if (cbLibro2.getValue() != null && !txtLibro2.equals("")) {
                String sql2 = "{call nuevo_registro_set_cliente(?, ?)}";
                CallableStatement stmt2 = con.prepareCall(sql2);
                stmt2.setString(1, cbLibro2.getValue().getIsbn());
                stmt2.setInt(2, Integer.parseInt(txtLibro2.getText()));
                stmt2.execute();
            }
            if (cbLibro3.getValue() != null && !txtLibro3.equals("")) {
                String sql3 = "{call nuevo_registro_set_cliente(?, ?)}";
                CallableStatement stmt3 = con.prepareCall(sql3);
                stmt3.setString(1, cbLibro3.getValue().getIsbn());
                stmt3.setInt(2, Integer.parseInt(txtLibro3.getText()));
                stmt3.execute();
            }
            if (cbLibro4.getValue() != null && !txtLibro4.equals("")) {
                String sql4 = "{call nuevo_registro_set_cliente(?, ?)}";
                CallableStatement stmt4 = con.prepareCall(sql4);
                stmt4.setString(1, cbLibro4.getValue().getIsbn());
                stmt4.setInt(2, Integer.parseInt(txtLibro4.getText()));
                stmt4.execute();
            }
            if (cbLibro5.getValue() != null && !txtLibro5.equals("")) {
                String sql5 = "{call nuevo_registro_set_cliente(?, ?)}";
                CallableStatement stmt5 = con.prepareCall(sql5);
                stmt5.setString(1, cbLibro5.getValue().getIsbn());
                stmt5.setInt(2, Integer.parseInt(txtLibro5.getText()));
                stmt5.execute();
            }
            if (cbLibro6.getValue() != null && !txtLibro6.equals("")) {
                String sql6 = "{call nuevo_registro_set_cliente(?, ?)}";
                CallableStatement stmt6 = con.prepareCall(sql6);
                stmt6.setString(1, cbLibro6.getValue().getIsbn());
                stmt6.setInt(2, Integer.parseInt(txtLibro6.getText()));
                stmt6.execute();
            }
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
