/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Server.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;




public class PatientManager implements PatientManagerInterface  {

   private Connection c;
	
	public PatientManager(Connection connection) {
		this.c=connection;
	}
	
    /**
     *Insert into table Reports a new report of the patient 
     * @param rep Object of type Report with all its attributes
     */
    public void addDailyreport(Report rep) {
		try {
     
			String sql = "INSERT INTO Reports (patient_dni, report_date, quality, exhaustion,hours,movement,time, rest,awake,times,worries, mood, doubts)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
                                                prep.setDate(2, (java.sql.Date) rep.getTodaysDate());
			prep.setString(3, rep.getsleepQuality());
			prep.setString(4, rep.getExhaustion());
			prep.setString(5, rep.getAverageHours());
                                                prep.setString(6, rep.getMovement());
                                                prep.setString(7, rep.gettimeToFallAsleep());
                                                prep.setString(8, rep.getRest());
                                                prep.setString(9, rep.getStayAwake());
                                                prep.setString(10, rep.getTimesAwake());
                                                prep.setString(11, rep.getWorries());
                                                prep.setString(12, rep.getTodaysMood());
                                                prep.setString(13, rep.getdoubtsForDoctor());
			prep.executeUpdate();
			prep.close();
			}
		catch(Exception e) {
			e.printStackTrace();
			}
	}
            
    /**
     *Obtaining all the information of the report knowing the dni of the patient
     * @param dni
     * @return an ArrayList of Reports that the patient with that DNI has
     */
    public ArrayList<Report> reportHistory(String dni){
        ArrayList<Report> repList = new ArrayList<Report>();
        try {
                String sql = "SELECT * FROM Reports WHERE patient_dni LIKE ?";
                PreparedStatement prep = c.prepareStatement(sql);
                prep.setString(1, "%"+dni+"%");
                ResultSet rs = prep.executeQuery();
                
                while (rs.next()) {
                    String dni1=rs.getString("patient_dni");
                    java.util.Date repdate=rs.getDate("report_date");
                    String quality=rs.getString("quality");
                    String exhaust=rs.getString("exhaustion");
                    String averageHours=rs.getString("hours");
                    String movem=rs.getString("movement");
                    String timeToFall=rs.getString("time");
                    String res=rs.getString("rest");
                    String awake=rs.getString("awake");
                    String timAwake=rs.getString("times");
                    String dreams=rs.getString("dreams");
                    String worr=rs.getString("worries");
                    String mood=rs.getString("mood");
                    String doubts=rs.getString("doubts");
                    Report repnew = new Report (dni,repdate, quality, exhaust, averageHours, movem, timeToFall, res, awake, timAwake,dreams, worr, mood, doubts);
                    repList.add(repnew);
                }

        } catch (Exception e) {
                e.printStackTrace();
        }
        return repList;
    }

    /**
     *Getting an specific report of a patient specifying the Date of the report 
     * @param dateReport date of the report you are looking for
     * @return the Report with the corresponding information 
     */
    @Override
    public  Report getDailyReport(java.util.Date  dateReport){
                Report newreport = new Report();
                String sql = "SELECT * FROM Reports WHERE Report_date LIKE ?";
        try {
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, "%"+dateReport+"%");
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                String dni1=rs.getString("patient_dni");
                java.util.Date repdate=rs.getDate("report_date");
                String quality=rs.getString("quality");
                String exhaust=rs.getString("exhaustion");
                String averageHours=rs.getString("hours");
                String movem=rs.getString("movement");
                String timeToFall=rs.getString("time");
                String res=rs.getString("rest");
                String awake=rs.getString("awake");
                String timAwake=rs.getString("times");
                String dreams=rs.getString("dreams");
                String worr=rs.getString("worries");
                String mood=rs.getString("mood");
                String doubts=rs.getString("doubts");
                newreport = new Report(dni1,repdate, quality, exhaust, averageHours, movem, timeToFall, res, awake, timAwake, dreams, worr, mood, doubts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
                return newreport;
            }
            
    public  Report getReportByDni(String dni){
                Report newreport = new Report();
                String sql = "SELECT * FROM Reports WHERE patient_dni LIKE ?";
        try {
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, "%"+dni+"%");
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                String dni1=rs.getString("patient_dni");
                java.util.Date repdate=rs.getDate("report_date");
                String quality=rs.getString("quality");
                String exhaust=rs.getString("exhaustion");
                String averageHours=rs.getString("hours");
                String movem=rs.getString("movement");
                String timeToFall=rs.getString("time");
                String res=rs.getString("rest");
                String awake=rs.getString("awake");
                String timAwake=rs.getString("times");
                String dreams=rs.getString("dreams");
                String worr=rs.getString("worries");
                String mood=rs.getString("mood");
                String doubts=rs.getString("doubts");
                newreport = new Report(dni1,repdate, quality, exhaust, averageHours, movem, timeToFall, res, awake, timAwake, dreams, worr, mood, doubts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
                return newreport;
            }
    /**
     * Show all the patients that are in the Table Patients in the Database
     * @return an ArrayList of all the patients 
     */
    public  ArrayList<Patient> showPatients() {
		ArrayList<Patient> patList = new ArrayList<Patient>();
                //Connection c1 = null; //ESTO NO ES ASÃ, SÃ“LO QUE HAY QUE INICIALIZARLA PARA QUE NO DE ERROR
		try {
			String sql = "SELECT * FROM Patients";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int patId = rs.getInt("patient_id");
				String patName = rs.getString("name");
				String patLastName = rs.getString("lastname");
				String patTele = rs.getString("telephone");
                                                                String patAddress = rs.getString("Address");
                                                                java.util.Date patdob=rs.getDate("dob");
                                                                String patdni = rs.getString("dni");
				String patgender = rs.getString("gender");
				Patient newPatient = new Patient(patId, patName, patLastName, patTele, patAddress, patdob,patdni, patgender);
				patList.add(newPatient);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return patList;
	}

    /**
     *Adding a Patient's object to the table Patients in the Database 
     * @param pat object of Patient with all its attributes 
     */
    @Override
            public void addpatientbyRegister(Patient pat) {
		try {
     
			String sql = "INSERT INTO Patients (name, lastname, telephone, address,DOB,dni,gender)"
					+ " VALUES (?,?,?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, pat.getName());
			prep.setString(2, pat.getLastname());
			prep.setString(3, pat.getTelephone());
                        prep.setString(4, pat.getAddress());
                        prep.setDate(5, (java.sql.Date) pat.getDateOfBirth());
                        prep.setString(6, pat.getDni());
                        prep.setString(7, pat.getGender());
			prep.executeUpdate();
			prep.close();
			}
		catch(Exception e) {
			e.printStackTrace();
			}
	}
  
    /**
     * Returns a Patient's object with all its attributes knowing the DNI
     * @param dni of the patient you are looking for 
     * @return the patient with this dni or null if there is not patient in the database with this dni
     */
    @Override
    public  Patient searchSpecificPatientByDNI(String dni){
         Patient patientfound=null;
            try {
                String sql = "SELECT * FROM Patients WHERE dni LIKE ?";
                PreparedStatement prep = c.prepareStatement(sql);
                prep.setString(1, "%"+dni+"%");
                ResultSet rs = prep.executeQuery();

                while(rs.next()) {
                        int id = rs.getInt("patient_id");
                        String name = rs.getString("name");
                        String lastname = rs.getString("lastname");
                        String tele = rs.getString("telephone");
                        String address = rs.getString("address");
                        java.util.Date dob=rs.getDate("dob");
                        String gender = rs.getString("gender");
                        patientfound = new Patient(id,name,lastname,tele,address,dob,dni,gender);
                }
            }catch(Exception e) {
                    e.printStackTrace();
            }
            return patientfound;
     }

    /**
     * Returns a patient object with known attributes knowing the dni
     * @param pat_id the dni of the patient you are looking for 
     * @return a patient's object with this dni
     */
    public  Patient getPatient(int pat_id) {
                Patient pat = new Patient();
                Connection c1 = null;
                
		try {
			String sql = "SELECT * FROM Patients WHERE patient_id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, pat_id);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("patient_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
                                                                String telephone = rs.getString("telephone");
                                                                String address = rs.getString("address");
                                                                java.util.Date patdob=rs.getDate("dob");
                                                                String dni = rs.getString("dni");
                                                                String gender = rs.getString("gender");
                                                                
                                pat.setId(id);
                                pat.setName(name);
                                pat.setLastname(lastname);
                                pat.setTelephone(telephone);
                                pat.setAddress(address);
                                pat.setDateOfBirth(patdob);
                                pat.setDni(dni);
                                pat.setGender(gender);
                                
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
		return pat;
		
	}     
     
    /**
     *Method that prints the values of the EEG of an specific patient 
     * @param dni of the patient you are looking for 
     */
    public void viewEEGString(String dni) {
        
         try {
            String sql1= "SELECT eeg FROM EEGs WHERE patient_dni LIKE ?";
            PreparedStatement prep1 = c.prepareStatement(sql1);
            prep1.setString(1, "%"+dni+"%");
            ResultSet rs2 = prep1.executeQuery();
            while (rs2.next()) {
                String eegString=rs2.getString("EEG");
                System.out.println("The eeg values are: "+eegString); //NI SIQUIERA SE HACE ESTO CUANDO LO DESCOMENTO!!!!!!
            }  	
        }catch (SQLException ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     
    /**
     * Method that prints the values of EEG with LUX of an specific patient
     * @param dni of the patient you are looking for 
     */
    public void viewEEGStringLUX(String dni) {
        
         try {
            String sql1= "SELECT eeg_lux FROM EEGs WHERE patient_dni LIKE ?";
            PreparedStatement prep1 = c.prepareStatement(sql1);
            prep1.setString(1, "%"+dni+"%");
            ResultSet rs2 = prep1.executeQuery();
            while (rs2.next()) {
                String eegString=rs2.getString("EEG_LUX");
                System.out.println("The eeg values with LUX are: "+eegString);
            }  	
        }catch (SQLException ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    /**
     *Gives an ArrayList of the signals of the patient you are looking for 
     * @param dni of the patient you are looking for 
     * @return an ArrayList of the signals obtained or that specific patient's has
     */
    @Override
    public ArrayList<Signals> viewEEGHistory(String dni) {
         ArrayList<Signals> eegs = new ArrayList<Signals>();
         ArrayList<Integer> values=new ArrayList<>();
         String[] valuesString;
         Connection c1 = null;
            try {
                        String sql1= "SELECT * FROM EEGs WHERE patient_dni LIKE ?";
                        PreparedStatement prep1 = c.prepareStatement(sql1);
			prep1.setString(1, "%"+dni+"%");
			ResultSet rs2 = prep1.executeQuery();
                        while (rs2.next()) {
                            String dni1=rs2.getString("patient_dni");
                            java.util.Date date = rs2.getDate("EEG_date");
                            String EEG=rs2.getString("EEG");
                            String eeg_lux=rs2.getString("EEG_LUX");
                            //LocalDate date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            //System.out.println(date1);
                            Signals eeg= new Signals(date,dni);
                            eegs.add(eeg);
                        }
		}catch(Exception e) {
			e.printStackTrace();
		}
            return eegs;
    }
	  

	
}



