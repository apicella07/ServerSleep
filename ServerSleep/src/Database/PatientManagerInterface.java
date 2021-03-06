/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Server.*;
import java.util.ArrayList;
import java.util.Date;




public interface PatientManagerInterface {
    public void addpatientbyRegister(Patient pat);
    public Patient searchSpecificPatientByDNI(String dni);
    public  ArrayList<Patient> showPatients();
    public  Patient getPatient(int pat_id);
    public void addDailyreport(Report rep);
    public ArrayList<Report> reportHistory(String dni);
    public  Report getDailyReport(java.util.Date  dateReport);
    public ArrayList<Signals> viewEEGHistory(String dni);
    public void viewEEGString(String dni);
    public void viewEEGStringLUX(String dni);
    public  Report getReportByDni(String dni);
    
    

}

