/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import controllers.LogInController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josue Cabezas
 */
public class ConnectionMySQL {

    Connection conn = null;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE = "MarletDB";
//    private static final String HOSTNAME = "localhost";
    private static final String HOSTNAME = "192.168.43.149";
    private static final String PORT = "3306";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE + "?useSSL=false";;

//    public static Connection conexionDB() {
//        try {
//            Class.forName(DRIVER);
//            Connection con = DriverManager.getConnection(URL, "client", "password");
//            System.out.println("Conexion prederterminada");
//            return con;
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(ConnectionMySQL.class.getName()).log(Level.SEVERE, null, ex);
//            LogInController.mostrarDialogo("La base de datos no se encuentra disponible", "Error de conexión", "Error");
//            return null;
//        }
//    }
    
    public static Connection conexionDB(String usuario, String contrasena) {
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL, usuario, contrasena);
            System.out.println("Conexion usuario");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionMySQL.class.getName()).log(Level.SEVERE, null, ex);
            LogInController.mostrarDialogo("La base de datos no se encuentra disponible", "Error de conexión", "Error");
            return null;
        }
    }

}
