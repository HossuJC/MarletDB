/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.LogInController.usuarioActivo;
import static controllers.LogInController.con;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josue Cabezas
 */
public class HomeController implements Initializable {

    @FXML
    private Label lblNombre;
    @FXML
    private Label lblCedula;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblTipo;
    @FXML
    private Label btnCerrarSesion;
    @FXML
    private Button btnEditorial;
    @FXML
    private Button btnCliente;
    @FXML
    private Button btnLibro;
    @FXML
    private Button btnPedidoS;
    @FXML
    private Button btnPedidoE;
    @FXML
    private Button btnTransporte;
    @FXML
    private Button btnReporte;
    @FXML
    private StackPane panel;
    @FXML
    private VBox vbBotones;
    private VBox editorial = null;
    private VBox cliente = null;
    private VBox autor = null;
    private VBox libro = null;
    private VBox pedidoS = null;
    private VBox pedidoE = null;
    private VBox transporte = null;
    private VBox reporte = null;
    @FXML
    private Button btnAutor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblNombre.setText("Nombre:\n" + usuarioActivo.getNombres() + " " + usuarioActivo.getApellidos());
        lblCedula.setText("N. cédula:\n" + usuarioActivo.getCedula());
        lblEmail.setText("E-mail:\n" + usuarioActivo.getEmail());
        lblTipo.setText(usuarioActivo.getTipo());
    }

    @FXML
    private void cerrarSesion(MouseEvent event) {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Node nodo = (Node) event.getSource();
        Stage stage = (Stage) nodo.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/LogIn.fxml"))));
            stage.setMinWidth(350);
            stage.setMinHeight(430);
            stage.setWidth(350);
            stage.setHeight(430);
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cerrarSesionDesubrayar(MouseEvent event) {
        btnCerrarSesion.setUnderline(false);
    }

    @FXML
    private void cerrarSesionSubraayar(MouseEvent event) {
        btnCerrarSesion.setUnderline(true);
    }

    @FXML
    private void selecEditorial(ActionEvent event) {
        limpiarBotones();
        btnEditorial.setDisable(true);
        limpiarPanel();
        if (editorial == null) {
            try {
                editorial = FXMLLoader.load(getClass().getResource("/fxml/Editorial.fxml"));
                panel.getChildren().add(editorial);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            editorial.setVisible(true);
        }
    }

    @FXML
    private void selecCliente(ActionEvent event) {
        limpiarBotones();
        btnCliente.setDisable(true);
        limpiarPanel();
        if (cliente == null) {
            try {
                cliente = FXMLLoader.load(getClass().getResource("/fxml/Cliente.fxml"));
                panel.getChildren().add(cliente);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            cliente.setVisible(true);
        }
    }

    @FXML
    private void selecAutor(ActionEvent event) {
        limpiarBotones();
        btnAutor.setDisable(true);
        limpiarPanel();
        if (autor == null) {
            try {
                autor = FXMLLoader.load(getClass().getResource("/fxml/Autor.fxml"));
                panel.getChildren().add(autor);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            autor.setVisible(true);
        }
    }

    @FXML
    private void selecLibro(ActionEvent event) {
        limpiarBotones();
        btnLibro.setDisable(true);
        limpiarPanel();
        if (libro == null) {
            try {
                libro = FXMLLoader.load(getClass().getResource("/fxml/Libro.fxml"));
                panel.getChildren().add(libro);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            libro.setVisible(true);
        }
    }

    @FXML
    private void selecPedidoS(ActionEvent event) {
        limpiarBotones();
        btnPedidoS.setDisable(true);
        limpiarPanel();
        if (pedidoS == null) {
            try {
                pedidoS = FXMLLoader.load(getClass().getResource("/fxml/PedidoS.fxml"));
                panel.getChildren().add(pedidoS);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            pedidoS.setVisible(true);
        }
    }

    @FXML
    private void selecPedidoE(ActionEvent event) {
        limpiarBotones();
        btnPedidoE.setDisable(true);
        limpiarPanel();
        if (pedidoE == null) {
            try {
                pedidoE = FXMLLoader.load(getClass().getResource("/fxml/PedidoE.fxml"));
                panel.getChildren().add(pedidoE);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            pedidoE.setVisible(true);
        }
    }

    @FXML
    private void selecTransporte(ActionEvent event) {
        limpiarBotones();
        btnTransporte.setDisable(true);
        limpiarPanel();
        if (transporte == null) {
            try {
                transporte = FXMLLoader.load(getClass().getResource("/fxml/Transporte.fxml"));
                panel.getChildren().add(transporte);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            transporte.setVisible(true);
        }
    }

    @FXML
    private void selecReporte(ActionEvent event) {
        limpiarBotones();
        btnReporte.setDisable(true);
        limpiarPanel();
        if (reporte == null) {
            try {
                reporte = FXMLLoader.load(getClass().getResource("/fxml/Reporte.fxml"));
                panel.getChildren().add(reporte);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            reporte.setVisible(true);
        }
    }

    private void limpiarBotones() {
        for (Node b : vbBotones.getChildren()) {
            Button btn = (Button) b;
            btn.setDisable(false);
        }
    }

    private void limpiarPanel() {
        if (editorial != null) {
            editorial.setVisible(false);
        }
        if (cliente != null) {
            cliente.setVisible(false);
        }
        if (autor != null) {
            autor.setVisible(false);
        }
        if (libro != null) {
            libro.setVisible(false);
        }
        if (pedidoS != null) {
            pedidoS.setVisible(false);
        }
        if (pedidoE != null) {
            pedidoE.setVisible(false);
        }
        if (transporte != null) {
            transporte.setVisible(false);
        }
        if (reporte != null) {
            reporte.setVisible(false);
        }
    }

}
