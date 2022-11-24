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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pojos.Patient;

/**
 *
 * @author agarc
 */
public class SceneChanger {
    
    public void ChangeSceneWithPatient(ActionEvent event, String viewName, Patient patient,UpdatePatientController controllerClass) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Scene scene = new Scene(parent);

        // access the controller class and preload
        controllerClass = loader.getController();
        controllerClass.DataFromPatient(patient);

        // get the stage from the event that was passed in
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
    }
    
   public void ChangeSceneWithSignal(ActionEvent event, String viewName, Patient patient, SignalsPatientController controllerClass) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Scene scene = new Scene(parent);

        // access the controller class and preload
        controllerClass = loader.getController();
        controllerClass.DataFromPatient(patient);

        // get the stage from the event that was passed in
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
   }
}
