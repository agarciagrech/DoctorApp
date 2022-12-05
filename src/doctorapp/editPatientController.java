/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorapp;

import doctorUtilities.menu;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
public class editPatientController {
    @FXML
    private TextField txtMedCard;

    @FXML
    private TextField txtDiagnosis;

    @FXML
    private TextField txtAllergies;

    @FXML
    private Button updateDButton;

    @FXML
    private Button updateAButton;
    
     @FXML
    private Button consultPatients;
     
    @FXML
    private Button backButton;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private Button selectButton;
 
    
    

    @FXML
    void updateAllergies(ActionEvent event) {
       int medCard = Integer.parseInt(txtMedCard.getText());
       String newAllergy = txtAllergies.getText();
       menu.editPatientAllergies(newAllergy,medCard);
       

    }
    
    @FXML
    void exitApp(ActionEvent event) {
        menu.exit();
    }

    @FXML
    void updateDiagnosis(ActionEvent event) {
       int medCard = Integer.parseInt(txtMedCard.getText());
       String newDiagnosis = txtDiagnosis.getText();
       menu.editPatientDiagnosis(newDiagnosis,medCard);
    }
    
     @FXML
    void showPatients(ActionEvent event) throws IOException {
        URL url = new File("src/doctorapp/viewPatients.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }
    
    @FXML
    void back(ActionEvent event) throws IOException {
        menu.exitEditPatient();
        URL url = new File("src/doctorapp/menuDoctor.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

}
