/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorapp;

import doctorUtilities.CommunicationWithServer;
import doctorUtilities.MenuDoctor;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import pojos.Patient;

/**
 *
 * @author agarc
 */
public class UpdatePatientController implements InterfaceManager{
     @FXML
    private TextField txtname;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private Button updatePatientButton;

    @FXML
    private TextField txtdob;

    @FXML
    private Button backMenuButton;

    

    @FXML
    private TextField txtsurname;

    @FXML
    private ChoiceBox<?> RoleChoiceBox1;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtmacAddress;

    @FXML
    private TextField txtdiagnosis;

    @FXML
    private TextField txtmedCardNumber;

    @FXML
    private TextField txtpassword;
    
     @FXML
    private TextField txtallergies;

    @FXML
    private Button exitButton;

    @FXML
    void updatePatient(ActionEvent event) {
        ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");
        genderChoiceBox.setItems(genderList);
        genderChoiceBox.setValue("Male");
        
        String name = txtname.getText();
        String surname = txtsurname.getText();
        String address = txtaddress.getText();
        String email = txtemail.getText();
        String macAddress = txtmacAddress.getText();
        String diagnosis = txtdiagnosis.getText();
        String password = txtpassword.getText();
        String gender = genderChoiceBox.getValue();
        Date dob = new Date(txtdob.getText());
        Integer medCard = Integer.parseInt(txtmedCardNumber.getText());
        String allergies = txtallergies.getText();
        
        Patient p = new Patient(medCard,name, surname,dob, address,email, diagnosis, allergies, gender, macAddress );
        //MenuDoctor.AddPatient(p); //Hacer el update del patient
        ClearData();
        
        //CommunicationWithServer.sendPatient(pw,p);
        

    }

    @FXML
    void backtoMenu(ActionEvent event) throws IOException {
        URL url = new File("src/doctorapp/menuDoctor.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }
    
     @Override
    public void DataFromPatient(Patient p){
        
        
    }
    
    public void ClearData(){
        this.txtname.clear();
        this.txtsurname.clear();
        this.txtaddress.clear();
        this.txtemail.clear();
        this.txtmacAddress.clear();
        this.txtdiagnosis.clear();
        this.txtallergies.clear();
        this.txtdob.clear();
        this.txtmedCardNumber.clear();
        this.genderChoiceBox.setValue("Male"); //Default value 
    }

    

    @FXML
    void exitApp(ActionEvent event) {

    }
      
    
}
