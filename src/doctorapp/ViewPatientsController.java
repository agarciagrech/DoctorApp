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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pojos.Patient;

/**
 *
 * @author agarc
 */
public class ViewPatientsController {
    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, Integer> medCardCol;

    @FXML
    private TableColumn<Patient, String> nameCol;

    @FXML
    private TableColumn<Patient, String> surnameCol;

    @FXML
    private TextField filtro;

    @FXML
    private Button editButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button showSignals;

    @FXML
    private Button backMenuOption;

    @FXML
    void back(ActionEvent event) throws IOException {
        URL url = new File("src/doctorapp/menuDoctor.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void editPatient(ActionEvent event) {

    }

    @FXML
    void exitApp(ActionEvent event) {

    }

    @FXML
    void patientSelected(MouseEvent event) {

    }

    @FXML
    void search(KeyEvent event) {

    }

    @FXML
    void showPatientSignals(ActionEvent event) {

    }
    
}
