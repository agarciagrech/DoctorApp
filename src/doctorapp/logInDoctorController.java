/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author agarc
 */
public class logInDoctorController {
    @FXML
    private Button logInButton;

    @FXML
    private TextField doctorUsername;

    @FXML
    private TextField doctorPassword;

    @FXML
    private Button exitButton;

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void logIn(ActionEvent event) throws  IOException {
        URL url = new File("src/doctorapp/menuDoctor.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }
    
}
