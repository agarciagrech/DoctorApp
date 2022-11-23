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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class CommunicationWithServer {
     public static Socket connectToServer(String IPAddress) {
        Socket socket = new Socket();
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter (outputStream,true);
            BufferedReader bf = new BufferedReader (new InputStreamReader (inputStream));
            //System.out.println("Introduce your IP: ");
            //String ip = bf.readLine();
            //printWriter.println(ip); //si queremos mandarle el ip al server
            socket = new Socket(IPAddress, 9000);
        } catch (IOException ex) {
            Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socket;
    }
     
     public static void sendDoctor(PrintWriter pw, Doctor doctor) {
         pw.println(doctor.toString());
    }
    
    public static void sendPatient(PrintWriter pw,Patient patient) {
        pw.println(patient.toString());
    }
    
    public static void sendUser(PrintWriter printWriter, User user) {
        printWriter.println(user.toString());
    }
    
    public static Patient receivePatient(BufferedReader bf){
        Patient p = new Patient();
        try{
            String line = bf.readLine();
            line=line.replace("{", "");
            line=line.replace("Patient", "");
            String[] atribute = line.split(",");
            SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy"); 
        
            for (int i =0;i <atribute.length; i++){
                String[] data2 = atribute[i].split("=");
                for (int j =0;j <data2.length - 1; j++){
                    data2[j]=data2[j].replace(" ", "");
                    switch(data2[j]){
                        case "medical_card_number": 
                            p.setMedical_card_number(Integer.parseInt(data2[j+1]));
                            break;
                        case "name":
                            p.setName(data2[j+1]);
                            break;
                        case "surname":
                            p.setSurname(data2[j+1]);
                            break;
                         case "dob":
                            try {
                                p.setDob(format.parse(data2[j+1]));
                            } catch (ParseException ex) {
                                Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case "address": 
                            p.setAddress(data2[j+1]);
                            break;
                        case "email":
                            p.setEmail(data2[j+1]);
                            break;
                        case "diagnosis": 
                            p.setDiagnosis(data2[j+1]);
                            break;
                        case "allergies":
                            p.setAllergies(data2[j+1]);
                            break;
                        case "gender": 
                            p.setGender(data2[j+1]);
                            break;
                        case "userId": 
                            p.setUserId(Integer.parseInt(data2[j+1]));
                            break;
                        case "macAddress": 
                            p.setMacAddress(data2[j+1]);
                            break;
                    }
                }
            }
            System.out.println("Patient received:");
            System.out.println(p.toString());
        }catch(IOException ex){
            Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p; 
    }
    
    
    public static Doctor receiveDoctor(BufferedReader bufferReader){
        Doctor d= new Doctor();
        try{
            String line = bufferReader.readLine();
            line=line.replace("{", "");
            line=line.replace("Doctor", "");
            String[] atribute = line.split(",");
            for (int i =0;i <atribute.length; i++){
                String[] data2 = atribute[i].split("=");
                for (int j =0;j <data2.length - 1; j++){
                    data2[j]=data2[j].replace(" ", "");
                    switch(data2[j]){
                         case "name":d.setDname(data2[j+1]); 
                                     break;
                        case "surname":d.setDsurname(data2[j+1]);
                                        break;
                        case "email": d.setDemail(data2[j+1]); 
                                     break;
                        case "id":d.setDoctorId(Integer.parseInt(data2[j+1]));
                                        break;
                    }
                }
            }
            System.out.println("Doctor recieved:");
            System.out.println(d.toString());
        }catch(IOException ex){
            Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
    public static User receiveUser (BufferedReader br){
        User u = new User();
        try {
        String line = br.readLine();
        line=line.replace("{", "");
        line=line.replace("User", "");
        String[] atribute = line.split(",");
        for (int i =0;i <atribute.length; i++){
            String[] data2 = atribute[i].split("=");
            for (int j =0;j <data2.length - 1; j++){
                data2[j]=data2[j].replace(" ", "");
                switch(data2[j]){
                    case "username":
                        u.setUsername(data2[j+1]);
                        break;
                    case "password":
                        u.setPassword(data2[j+1]);
                        break;
                    case "role":
                        u.setRole(Integer.parseInt(data2[j+1]));
                        break;
                    case "userId":
                        u.setUserId(Integer.parseInt(data2[j+1]));
                        break;
                }
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public static String[] ShowSignals(BufferedReader bf, PrintWriter pw, Patient p){
        try {
            String[] filenames = null;
            // Pedimos al server que nos envie la lista de señales:
            pw.println("Send Signals");
            pw.println("Patient= "+p.getMacAddress());
            // VOY A ASUMIR QUE SE ENVIAN LOS FILENAME SEPARADOS POR \n
            String line = bf.readLine();
            while ((line = bf.readLine()) != null) {
                filenames =line.split("\n");
            }
            return filenames;
        } catch (IOException ex) {
            Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<String> receivePatientList(BufferedReader bf){
        List<String> patientList = new ArrayList();
        boolean stop= true;
        try {
            while(stop){
               String line = bf.readLine();
               if (!line.equalsIgnoreCase("End of list")) {
                   stop=true;
                   System.out.println(line);
                   patientList.add(line);
               }else{
                   stop=false;
               }
           }
        } catch (IOException ex) {
            Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patientList;
    }
    
    public static void exitFromServer(InputStream inputStream, OutputStream outputStream, Socket socket ){
        try{
            inputStream.close();
        }catch(IOException ex){
             Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            outputStream.close();
        }catch(IOException ex){
             Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            socket.close();
        }catch(IOException ex){
             Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean ReleaseResources(PrintWriter pw, BufferedReader br) {
        pw.close();
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     
}
