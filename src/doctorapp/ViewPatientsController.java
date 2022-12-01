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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
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
public class ViewPatientsController  {
    @FXML
    private TableView<Patient> patientTable;


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
    private Button showPatientsButton;
    
     @FXML
    private Button deleteButton;
    
    
    private static SceneChanger sc;
    
    

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
    void delete(ActionEvent event) throws IOException {
        menu.Delete();
        URL url = new File("src/doctorapp/deletePatient.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
        
        
    }
    
    @FXML
    void showPatients(ActionEvent event) {
        List<String> patientsList = menu.doctorsPatients();
        List<Patient> pList = new ArrayList();
        for(int i=0; i<patientsList.size(); i++){
            Patient p = new Patient();
            String line = patientsList.get(i);
            line = line.replace("Patient:", "");
            String[] atribute = line.split("/");
            String name = atribute[0];
            String surname = atribute[1];
            String medCard = atribute[2];
            p.setName(name);
            p.setSurname(surname);
            p.setMedical_card_number(Integer.parseInt(medCard));
            pList.add(p);
            patientTable.getItems().addAll(p);
        }
        

    }
    
    public void initialize() {
       patientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
       TableColumn<Patient,String> medCardCol = new TableColumn<>("MedCard");
       TableColumn<Patient,String> nameCol = new TableColumn<>("Name");
       TableColumn<Patient,String> surnameCol = new TableColumn<>("Surname");
       
       medCardCol.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getMedical_card_number())));
       nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
       
       patientTable.getColumns().addAll(medCardCol, nameCol, surnameCol);
       
    }
    
    
    
    /*public void SelectedPatient(){
       this.editButton.setDisable(false);
       this.showSignals.setDisable(false);
    }*/

    @FXML
    void editPatient(ActionEvent event) throws IOException {
        menu.editPatient();
        URL url = new File("src/doctorapp/editPatient.fxml").toURI().toURL();
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

   

    /*@FXML
    void search(KeyEvent event) {
         

            List<Patient> sortList = MenuDoctor.seeMyPatients();
            sortList.comparatorProperty().bind(patientTable.comparatorProperty());
            patientTable.setItems(sortList);

        

    }*/

    @FXML
    void showPatientSignals(ActionEvent event) throws IOException {
        menu.showSignnalList();
        URL url1 = new File("src/doctorapp/ShowSignals.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url1);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
        

    }

   
    
}
