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
import javafx.stage.Stage;

/**
 *
 * @author agarc
 */
public class MenuDoctorController {
    @FXML
    private Button registerPatientButton;

    @FXML
    private Button registerDoctorButton;

    @FXML
    private Button viewPatientsButton;

    @FXML
    private Button exitButton;

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void registerDoctor(ActionEvent event) throws IOException {
        URL url = new File("src/doctorapp/registerDoctor.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void registerPatient(ActionEvent event) throws IOException {
        URL url = new File("src/doctorapp/registerPatient.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    

    @FXML
    void viewPatients(ActionEvent event) {

    }

    
}
