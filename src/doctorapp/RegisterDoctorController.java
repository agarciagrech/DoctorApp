/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorapp;

import doctorUtilities.MenuDoctor;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojos.Doctor;

/**
 *
 * @author agarc
 */
public class RegisterDoctorController {
    @FXML
    private TextField txtname;

    @FXML
    private Button registerDoctorButton;

    @FXML
    private Button backMenuButton;

    
    @FXML
    private TextField txtsurname;


    @FXML
    private TextField txtemail;

   @FXML
    private TextField txtId;

    @FXML
    private Button exitButton;

    @FXML
    void backtoMenu(ActionEvent event) throws IOException{
        URL url = new File("src/doctorapp/menuDoctor.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void exitApp(ActionEvent event) {

    }

    @FXML
    void registerDoctor(ActionEvent event) {
        String name = txtname.getText();
        String surname = txtsurname.getText();
        Integer doctorId = Integer.parseInt(txtId.getText());
        String email = txtemail.getText();
        
        Doctor d = new Doctor(doctorId,name, surname,email);
        MenuDoctor.SendDoctor(d);

    }
}
