/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    package doctorapp;

import doctorUtilities.menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import pojos.Signal;

public class ShowSignalsController {

    @FXML
    private TextField txtFIlename;

    @FXML
    private Button backButton;

    @FXML
    private Button consultPatients;
    
    @FXML
    private Button showButton;

    @FXML
    private TextField txtMedCard;

    @FXML
    private Button consultFilenames;
    @FXML
    private LineChart<?, ?> Graph;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private Button DisplayButton;
    
    @FXML
    private TextField txtSignalFilename;
    
    @FXML
    private TextField SectionLabel;
    
    @FXML
    private Button SelectSignalButton;
        
    private String section;
    private List<Integer> data;

    @FXML
    void back(ActionEvent event) throws IOException {
        menu.BacktTomenu();
        URL url = new File("src/doctorapp/menuDoctor.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showPatients(ActionEvent event) throws IOException {
        menu.BacktToShowPatients();
        URL url = new File("src/doctorapp/viewPatients.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void showFilenames(ActionEvent event) {
        Window owner = consultFilenames.getScene().getWindow();
        int medcard = Integer.parseInt(txtMedCard.getText());
        List<String> list = menu.getSignalList(medcard);
        showAlert(Alert.AlertType.INFORMATION,owner,"Signals of patient",list.toString());
    }
    
    @FXML
    void showSignal(ActionEvent event) throws IOException{
        Window owner = showButton.getScene().getWindow();
        String filename = txtFIlename.getText();
        List<Integer> data = menu.showSignal(filename);
    }  
    
    @FXML
    void exitApp(ActionEvent event) {
         menu.exit();
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
       
    @FXML
    void SelectSignal(ActionEvent event) throws IOException {
       String filename;
       filename = txtSignalFilename.getText();
       data = menu.showSignal(filename);
    }
    
    @FXML
    void Display(ActionEvent event) throws FileNotFoundException, IOException {
        XYChart.Series series = new XYChart.Series();
        Graph.getData().clear();
        section = SectionLabel.getText();
       
        for (int i =0; i<600/*values.size()*/;i++){
            series.getData().add(new XYChart.Data(i,data.get(i + 600*(Integer.parseInt(section)))));
        }
        Graph.getData().addAll(series);
    }
}