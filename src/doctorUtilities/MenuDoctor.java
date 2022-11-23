/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorUtilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Doctor;
import pojos.Patient;
import pojos.User;

/**
 *
 * @author agarc
 */
public class MenuDoctor {
    
        public static Socket socket;
        public static PrintWriter printWriter;
        public static BufferedReader bf; 
        
        
    public static void ConnectToServer(String IPAddress){
        try{
            socket = CommunicationWithServer.connectToServer(IPAddress);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            printWriter = new PrintWriter (outputStream,true);
            bf = new BufferedReader (new InputStreamReader (inputStream));
            
        }catch(IOException ex) {
            Logger.getLogger(MenuDoctor.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public static boolean logInDoctor(String username, String password) throws IOException{
          boolean logInCheck = true;
          User user = new User();
          user.setUsername(username);
          user.setPassword(password);
          
           CommunicationWithServer.sendUser(printWriter, user);
           String line = bf.readLine();
           if(line.equals("Wrong username or password")){
               logInCheck = false;
           }else{
               logInCheck = true; 
           }
          return logInCheck;
    }
    
    public static List<Patient> seeMyPatients(){ //Devuelve el los pacientes en ToString
        List<String> PatientsList = new ArrayList<>();
        PatientsList = CommunicationWithServer.receivePatientList(bf);
        List<Patient> doctorPatients = new ArrayList<>();
        
        for(int i = 0; i<PatientsList.size(); i++){
            String line = PatientsList.get(i);
            Patient pList = CommunicationWithServer.receivePatient(); //HAY QUE VER QUE PONEMOS
            doctorPatients.add(pList);
        }
        return doctorPatients;
       
        
    }
    
    public static void AddPatient(Patient p){
        CommunicationWithServer.sendPatient(printWriter, p);
    }
    
    public static void SendDoctor(Doctor d){
        CommunicationWithServer.sendDoctor(printWriter, d);
    }
            
    
}


