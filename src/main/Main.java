/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Josue Cabezas
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LogIn.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMinWidth(350);
            stage.setMinHeight(430);
            stage.setTitle("Marlet Representaciones - Base de Datos");
            stage.getIcons().add(new Image("/Images/MarletIcon.jpg"));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
