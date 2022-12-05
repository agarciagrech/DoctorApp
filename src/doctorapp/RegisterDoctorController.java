/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorapp;


import doctorUtilities.menu;
import static doctorapp.logInDoctorController.infoMessage;
import static doctorapp.logInDoctorController.showAlert;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
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
        menu.exit();

    }

    @FXML
    void registerDoctor(ActionEvent event) throws IOException {
         Window owner = registerDoctorButton.getScene().getWindow();
      
        if(txtname.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter the doctor's name");
            return;
        }
        if(txtsurname.getText().isEmpty()){
             showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter the doctor's surname");
             return;
        }
        
        if(txtemail.getText().isEmpty()){
             showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter the doctor's email");
             return;
        }
        String name = txtname.getText();
        String surname = txtsurname.getText();
        
        String email = txtemail.getText();
        
        boolean validData = ComprobarData(name, surname);
        
        if(validData == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing data");
            alert.setHeaderText("Please check the data");
            alert.showAndWait();
        }else{
           String usernamePass = menu.createDoctor(name, surname, email);
        
        if(usernamePass==null){
             infoMessage("Please enter the data correctly", null, "Failed");
        }else{
             try{
                showAlert2(Alert.AlertType.INFORMATION, owner,"Your Username and Password are",usernamePass);
                URL url = new File("src/doctorapp/menuDoctor.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);    
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        }
       
         
        
        

    }
    
    public boolean ComprobarData(String name, String surname){
        char[] chars = name.toCharArray();
        boolean validData = true;

        for (char c : chars) {
            if (Character.isDigit(c)) {
                validData = false;
                this.txtname.clear();
                break;
            }
        }
        
        char[] chars2 = surname.toCharArray();
        for(char c1: chars){
            if(Character.isDigit(c1)){
                validData = false;
                this.txtsurname.clear();
                break;
            }
           
        }

        if (this.txtname.getText().equals("")) {
            validData = false;
           
        }
        if (this.txtsurname.getText().equals("")) {
            validData = false;
           
        }
        return validData;
    }
    
     public static void infoMessage(String infoMessage, String headerText, String title) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
       }

       public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message ) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
       }
       

       public static void showAlert2(Alert.AlertType alertType, Window owner, String title, String message ) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
       }

    
}
