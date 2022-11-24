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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pojos.Doctor;
import pojos.Patient;

/**
 *
 * @author agarc
 */
public class ViewPatientsController implements Initializable {
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
    
    private ObservableList<Patient> Doctorpatients = FXCollections.observableArrayList();
    FilteredList filter = new FilteredList(Doctorpatients, e -> true);
    private static SceneChanger sc;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       this.editButton.setDisable(true);
       this.showSignals.setDisable(true);
       
       medCardCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("medical_card_number"));
       nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
       surnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
       
       patientTable.setEditable(true);
       loadPatients();
       patientTable.setItems(Doctorpatients);
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        URL url = new File("src/doctorapp/menuDoctor.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
        
        
    }
    
    public void loadPatients(){
        List<Patient> patientsList = new ArrayList();
        patientsList = CommunicationWithServer.receivePatientList();
        
        int i;
        Patient p;
        
        for(i=0; i<patientsList.size(); i++){
            p = patientsList.get(i);
            this.Doctorpatients.add(p);
        }
    }
    
    public void SelectedPatient(){
       this.editButton.setDisable(false);
       this.showSignals.setDisable(false);
    }

    @FXML
    void editPatient(ActionEvent event) throws IOException {
        sc = new SceneChanger();
        Patient chosenPatient = this.patientTable.getSelectionModel().getSelectedItem();
        UpdatePatientController controller = new UpdatePatientController();
        sc.ChangeSceneWithPatient(event, "registerPatient.fxml", chosenPatient, controller);

    }

    @FXML
    void exitApp(ActionEvent event) {

    }

   

    /*@FXML
    void search(KeyEvent event) {
         

            List<Patient> sortList = MenuDoctor.seeMyPatients();
            sortList.comparatorProperty().bind(patientTable.comparatorProperty());
            patientTable.setItems(sortList);

        

    }*/

    @FXML
    void showPatientSignals(ActionEvent event) {

    }

   
    
}
