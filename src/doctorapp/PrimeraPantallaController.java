/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorapp;

import doctorUtilities.menu;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author agarc
 */
public class PrimeraPantallaController {
    
    @FXML
    private Button logInButton;
    
    @FXML
    private TextField ipAddress;

    @FXML
    void logIn(ActionEvent event) {
        
        String ip = ipAddress.getText();
        try{
            menu.initiliazeStreams(ip);
            URL url = new File("src/doctorapp/logInDoctor.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);    
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        }catch (java.net.ConnectException ce) {
            infoMessage("Please enter a correct IP Address", null, "Failed");
        } catch (IOException ex) {
            System.out.println("IOEXCEPTION");
        }
    }
    
      
    public static void infoMessage(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
