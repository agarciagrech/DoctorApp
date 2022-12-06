/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doctorUtilities;


import java.rmi.NotBoundException;

import java.security.*;
import java.util.*;
import java.sql.Date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Doctor;
import pojos.Patient;
import pojos.Signal;
import pojos.User;


public class menu {

    public static InputStream console = (System.in);
    public static Socket socket;
    public static PrintWriter pw;
    public static BufferedReader br;
    public static InputStream inputStream;
    public static OutputStream outputStream;
    
    
    public static void initiliazeStreams(String IPAddress) throws IOException {
            socket = utilities.CommunicationWithServer.connectToServer(IPAddress);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            pw = new PrintWriter(outputStream, true);
            br = new BufferedReader(new InputStreamReader(inputStream));
            pw.println(2);
    }
    
    public static void exit(){
        pw.println(0);
        pw.println(0);
        utilities.CommunicationWithServer.ReleaseResources(pw, br);
        utilities.CommunicationWithServer.exitFromServer(inputStream, outputStream, socket);
        
        System.exit(0);
    }
   
       
   

                  
    public static boolean login(String username, String password) throws Exception{
        pw.println(2);
        boolean logInCorrect = false;
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        utilities.CommunicationWithServer.sendUser(pw, user);
        String line = br.readLine();
        if(line.equals("Wrong username or password")) {
            logInCorrect = false;
            pw.println(2);
        } else if(line.equals("patient")){
                logInCorrect = false;
                pw.println(1);
                pw.println(2);
        } else if (line.equals("doctor")){
                logInCorrect = true;
                pw.println(0);
                 Doctor d = utilities.CommunicationWithServer.receiveDoctor(br);
               
        }
        return logInCorrect;
    }
    
    
    

    
    public static void BacktToShowPatients(){
        pw.println(2);
    }
    public static void BacktTomenu(){
        pw.println(0);
    }
    
    public static void Delete(){
        pw.println(5);
    }
    
    public static void DeletePatient(Integer medCard){
        List<String> pList = utilities.CommunicationWithServer.receivePatientList(br);
        pw.println(medCard);
    }
    
    public static List<String> doctorsPatients(){
        pw.println(2);
        
        List<String> pList = utilities.CommunicationWithServer.receivePatientList(br);
        Patient p = new Patient();
        return pList;
    }
     public static void showSignnalList(){
        pw.println(4);
        List<String> pList = utilities.CommunicationWithServer.receivePatientList(br);
    }
     public static List<String> getSignalList(int medcard){
         pw.println(medcard);
         List<String> sList = utilities.CommunicationWithServer.ShowSignals(br, pw);
        return sList;
     }
    public static List<Integer> showSignal(String filename) throws IOException{
        Signal s;
        pw.println(filename);
        if (filename.contains("ECG")){
            s= utilities.CommunicationWithServer.receiveECGSignal(br);
            System.out.println(s.getECG_values());
            return s.getECG_values();
        }else{
            s=utilities.CommunicationWithServer.receiveEMGSignal(br);
            System.out.println(s.getEMG_values());
            return s.getEMG_values();
        }
    }
    public static void editPatient(){
        pw.println(3);
       
    }
    
    public static void exitEditPatient(){
        pw.println(0);
    }
    
    public static void sendMedCard(Integer medCard){
       
        
    }

    public static void editPatientDiagnosis(String newDiagnosis, Integer medCard){
       pw.println(1);
      utilities.CommunicationWithServer.receivePatientList(br);
       pw.println(medCard);
       Patient p = utilities.CommunicationWithServer.receivePatient(br);
     
      
     
      
      p.setDiagnosis(newDiagnosis);
      utilities.CommunicationWithServer.sendPatient(pw, p);
    }

    public static void editPatientAllergies(String newAllergy, Integer medCard){
        pw.println(2);
        utilities.CommunicationWithServer.receivePatientList(br);
        pw.println(medCard);
        Patient p = utilities.CommunicationWithServer.receivePatient(br);
        
       
        
        p.setAllergies(newAllergy);
        utilities.CommunicationWithServer.sendPatient(pw, p);
    }    

   

    

    public static String createDoctor(String name, String surname, String email) throws IOException{
        
        //Doctor doctor = utilities.CommunicationWithServer.receiveDoctor(br);
        pw.println(1);
        boolean registerCorrect = false;
        Doctor d = new Doctor();
        d.setDname(name); 
        d.setDsurname(surname);
        d.setDemail(email);
        
        utilities.CommunicationWithServer.sendDoctor(pw, d);
        User user = utilities.CommunicationWithServer.receiveUser(br);
        String userPassword = user.getPassword();
        String userName = user.getUsername();
        String userpass= "Username: " + userName +"\n Password: " + userPassword;
        
        String line = br.readLine();
        if (line.equals("Doctor successfully registered")){
            registerCorrect = true;
            return userpass;
        } else{
            registerCorrect = false;
            return null;
        }
        
    }
    
 
    
  
}
