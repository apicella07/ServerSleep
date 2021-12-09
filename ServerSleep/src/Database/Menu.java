/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Server.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.sql.Connection;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Menu {
    private static Database.DBManagerInterface dbman;
    private static PatientManagerInterface pmi;
    private static BufferedReader br;
    private static Patient patientUsing = new Patient();
    private static int num,numUsing;
    private static boolean inUse;
    private static boolean patFound;

    public static void main(String[] args) throws IOException, ParseException, Exception {
        dbman = new DBManager();
        dbman.connect();
        pmi = dbman.getPatientManager();
      
        br = new BufferedReader(new InputStreamReader(System.in));
        
        
        inUse=false;
        patFound=false; 
        int max;
        System.out.println("WELCOME TO SLEEP CONTROL MANAGER\n");
        
        while(true){
           System.out.println("Press 1 to introduce the DNI of the patient\n ");
            max=1;
            if(patFound){
                 System.out.println("\nWhat do you want to do?\n"+"2.View patient's EEG values.\n"+"3.View patient's EEG with LUX values.\n"+"4.View patient's EEG history.\n"+"5.View patient's report history.\n"+"6.View patient's personal information.\n"+"7.Go search for another patient.");//+"8.Receive an EEG of your patient.");
                max=7;
            }
            System.out.println("0. Exit.\n");
            num=requestNumber(max);
            numUsing=num;
            inUse=true;
            
            while(inUse){
                switch(numUsing){
                    case 1:
                        searchbyDNI(); //Now variable patientUsing is the patient with this DNI
                        if(patFound){
                            System.out.println("You are already looking for a patient.");
                            break;
                        }
                        break;
                    case 2:
                        viewEEG(patientUsing.getDni()); 
                        break;
                    case 3:
                        viewEEGLUX(patientUsing.getDni()); 
                        break;
                    case 4:
                        viewEEGHistory(patientUsing.getDni());
                        break;
                    case 5:
                        reportHistory(patientUsing.getDni());
                        break;
                    case 6:
                        System.out.println(patientUsing.toString());
                        break;
                    case 7:
                        inUse=false;
                        patFound=false;
                        patientUsing=new Patient();
                        break;
                    /*case 6:
                        //receivePatient();
                        //receiveEEG(); in real time?
                        break;*/
                    default:
                        inUse=false;
                        patFound=false;  
                        System.exit(0);
                        break;
                        
                }
                break;
            }
      
            pressEnter();
        }

    }
    
    public static void searchbyDNI() throws IOException{
        boolean check = true;
        do {
            System.out.println("Type the dni of the patient you want to search" );
            String dniobtained = br.readLine();
            patientUsing = pmi.searchSpecificPatientByDNI(dniobtained);
            if (patientUsing!=null) {
                     System.out.println("The patient you obtained is: " +patientUsing);
                     patFound =true;
                     break;
            }
            else{
                System.out.println("Wrong DNI, please select an option: ");
                System.out.println("1. Introduce them again. ");
                System.out.println("0. Go back to the menu. ");
                 int option = requestNumber(2);
                    switch (option) {
                        case 1:
                                break;
                        case 0:
                                check = false;
                                break;
                    }
            }
        } while(check);
        
       
    }
    public static void viewEEG(String dni) throws IOException {
       pmi.viewEEGString(dni); 

    }
    public static void viewEEGLUX(String dni) {
        pmi.viewEEGStringLUX(dni); 
    }
    
       public static void getReport() throws IOException{
         LocalDate data= ui.takeDate(br,"Type the day of the report you want to get like this yyyy-MM-dd");
        java.util.Date repsday = java.sql.Date.valueOf(data);
        
        Report newrepobtained = pmi.getDailyReport(repsday);
        System.out.println("The report is: " +newrepobtained);
    }
       
       public static void reportHistory(String dni){
            ArrayList<Report> reps = new ArrayList<Report>();

          Report newrepo;
          reps = pmi.reportHistory(dni);
          Iterator it = reps.iterator();

          while(it.hasNext()){
              newrepo = (Report) it.next();
              System.out.println(newrepo.toString());
              System.out.println("");
          }
       }
   
         public static void viewEEGHistory(String dni){
          ArrayList<Signals> eegs = new ArrayList<Signals>();
          Signals eeg;
          eegs = pmi.viewEEGHistory(dni);
          Iterator it = eegs.iterator();
          while(it.hasNext()){
              eeg = (Signals) it.next();
              System.out.println(eeg.toStringWithoutValues());
              System.out.println("");
          }
       }
      
       public static int requestNumber(int max) {
		int num;
		do {

			num = ui.takeInteger(br, "Introduce the number: ");

		} while (ui.CheckOption(num, max));

		return num;
	}
        public static void pressEnter() {
		System.out.println("Press enter to go to the main menu and continue.");
		try {
			String nothing;
			nothing = br.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

       
}



