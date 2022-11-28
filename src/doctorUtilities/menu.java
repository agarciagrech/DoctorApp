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
    
    
    public static void initiliazeStreams(String IPAddress) {
        try {
            socket = utilities.CommunicationWithServer.connectToServer(IPAddress);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            pw = new PrintWriter(outputStream, true);
            br = new BufferedReader(new InputStreamReader(inputStream));
            pw.println(2);

        } catch (IOException ex) {
            Logger.getLogger(MenuDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
       
   

                  
    public static boolean login(String username, String password) throws Exception{
        boolean logInCorrect = false;
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        utilities.CommunicationWithServer.sendUser(pw, user);
        String line = br.readLine();
        if(line.equals("Wrong username or password")) {
            logInCorrect = false;
        } else if(line.equals("patient")){
                //patientMenu(socket, inputStream, outputStream, br, pw, user.getUserId());
                logInCorrect = true;
                Doctor d = utilities.CommunicationWithServer.receiveDoctor(br);
        } else if (line.equals("doctor")){
                logInCorrect = true;
                Doctor d = utilities.CommunicationWithServer.receiveDoctor(br);
                //doctorMenu(socket, inputStream, outputStream, br, pw);
               
        }
        return logInCorrect;
    }
    
    
    
    
    
        
        
    private static void doctorMenu(Socket socket, InputStream inputStream, OutputStream outputStream, BufferedReader bf, PrintWriter pw) throws Exception {
        Scanner sc = new Scanner (System.in);
        String trashcan;
        int option=0;
        Doctor doctor = utilities.CommunicationWithServer.receiveDoctor(bf);
        System.out.println("Hello Dr. " + doctor.getDsurname());
        do{
            int a = 0;
            
            
            System.out.println("Choose an option[0-2]:");
            System.out.println("\n1.Register a new Doctor \n2. See list of all my patients \n3. Edit Patient \n4. Consult recordings of a patient  \n 0. Exit");
            do {
                try {
                    option = sc.nextInt();
                    pw.println(option);
                    a = 1;
                } catch (Exception e) {
                    trashcan = sc.next();
                    System.out.println("Please select a valid option.");
                }
            } while (a==0);
       
            switch(option) {
            case 0:
                System.out.println("Thank you for using our system");
                utilities.CommunicationWithServer.ReleaseResources(pw, bf);
                utilities.CommunicationWithServer.exitFromServer(inputStream, outputStream, socket);
                break;
            case 1: 
                System.out.println("Register a new Doctor");
                //createDoctor(bf, pw);
                break;
            case 2:
                System.out.println("See list of all my patients");
                utilities.CommunicationWithServer.receivePatientList(bf);
                break;
            case 3:
                System.out.println("Edit Patient");
                utilities.CommunicationWithServer.receivePatientList(bf);
                System.out.println("Introduce medcard of patient to update:");
                int medcard = sc.nextInt();
                editPatient(bf,pw, medcard);
                break;
            case 4:
                System.out.println("Consult recordings of a patient");
                utilities.CommunicationWithServer.receivePatientList(bf);
                System.out.println("Introduce medcard of patient to update:");
                int medcard2 = sc.nextInt();
                pw.println(medcard2);
                showSignals(bf, pw);
                break;
            case 5:
                System.out.println("Delete Patient");
                /*utilities.CommunicationWithServer.receivePatientList(bf);
                System.out.println("Introduce medcard of patient to delete:");
                int medcard3 = sc.nextInt();
                //pw.println(medcard3);
                deletePatient(bf, pw, medcard3);*/
                break;
            default:
                System.out.println("Not a valid option.");
                break;
            }
        } while(true);
    }
    
    public static List<String> doctorsPatients(){
        pw.println(2);
        
        List<String> pList = utilities.CommunicationWithServer.receivePatientList(br);
        Patient p = new Patient();
        return pList;
    }
     public static void showSignnalList(){
        pw.println(4);
        
    }
     public static List<String> getSignalList(int medcard){
         pw.println(medcard);
         List<String> sList = utilities.CommunicationWithServer.ShowSignals(br, pw);
        return sList;
     }
    public static Signal selectsignal(String filename){
        pw.println(filename);
        Signal s = utilities.CommunicationWithServer.receiveSignal(br);
        return s;
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

   

    private static Patient selectPatient(BufferedReader br, PrintWriter pw) throws Exception{
        Scanner sc = new Scanner (System.in);
        //Show list with all patients.
        List<String> CompletePatientList = utilities.CommunicationWithServer.receivePatientList(br);
        for(int i =0;i<CompletePatientList.size();i++){
                System.out.println(CompletePatientList.get(i));
        }
        //Chose a Patient
        List<Patient> patientList = new ArrayList();
        Patient patient = null;
        while(patientList.isEmpty()){
            Integer medCard=null;
            System.out.println(patientList.toString());
            System.out.println("Enter the medical card number of the chosen patient: ");
            try{
                medCard = sc.nextInt();
            }catch(Exception ex){
                System.out.println("Not a valid medical card number ONLY NUMBERS");
            }
            pw.print(medCard);
            patient = utilities.CommunicationWithServer.receivePatient(br);
        }
        return patient; 
    }

    
    private static void deletePatient(BufferedReader br, PrintWriter pw, Integer medCard) throws Exception {
        //Scanner sc = new Scanner (System.in);
        //Show list with all patients.
        //List<String> CompletePatientList = utilities.CommunicationWithServer.receivePatientList(br);
        /*for(int i =0;i<CompletePatientList.size();i++){
                System.out.println(CompletePatientList.get(i));
        }
        
        //Chose a Patient to delete
        System.out.println("Introduce de medical card number of the patient to delete: ");
        String medcard = sc.next();
        pw.println("Medical Card= " + medcard);*/
        pw.println(medCard);
        //Patient p = utilities.CommunicationWithServer.receivePatient(br);
        
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
    
   
    
    
    private static void showSignals (BufferedReader br, PrintWriter pw) throws Exception{
        //int size = Integer.parseInt(br.readLine());
        Scanner sc = new Scanner (System.in);
        //Show list with all signals
        List<String> signalFilenames = utilities.CommunicationWithServer.ShowSignals(br, pw);
        System.out.println(signalFilenames.size());
        
        for(int i=0; i<signalFilenames.size();i++){
            System.out.println(signalFilenames.get(i));
        }
       
            System.out.println("Introduce filename of the signal:");
            String signalName = sc.next();
            pw.println(signalName);
           
            String signal = br.readLine();
            System.out.println(signal);
            
        //}
    }
    
   
    
    private static void editPatient (BufferedReader bf,PrintWriter pw, int medcard) throws Exception{
        Scanner sc = new Scanner (System.in);
        int option=1;
        String update;
        pw.println(medcard);
        Patient p = utilities.CommunicationWithServer.receivePatient(bf);
        while(option != 0) {
            System.out.println("Choose an option[0-2]:");
            System.out.println("\n0. Back \n1. Diagnosis \n2. Allergies");
            option = sc.nextInt();
            pw.println(option);
            switch(option) {
                case 0:
                    option=0;
                    break;
                case 1:
                        System.out.println("Write diagnosis:");
                        update = sc.next();
                        p.setDiagnosis(update);
                        utilities.CommunicationWithServer.sendPatient(pw, p);
                        break;
                case 2:
                        System.out.println("Write Allergies:");
                        update = sc.next();
                        p.setAllergies(update);
                        utilities.CommunicationWithServer.sendPatient(pw, p);
                        break;
                default:
                     System.out.println("Not valid option");
                     break;
            }
        }
    }
    
    private static void updateMacAddress (PrintWriter pw, Patient p) throws Exception{
       Scanner sc = new Scanner (System.in);
        String update;
        System.out.println("Write Bitalino MacAddress:");
        update = sc.next();
        p.setMacAddress(update);
        utilities.CommunicationWithServer.sendPatient(pw, p);
    }
}
