/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversleepapp;

import Database.PatientManager;
import Server.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.control.*;
import java.util.Date;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import Database.PatientManagerInterface;
import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * FXML Controller class
 *
 * @author 99lorena
 */
public class AccessInformationController implements Initializable {


    /**
     * Initializes the controller class.
     */
    
 
    @FXML 
    private Button search;
    @FXML 
    private Button goback;
    @FXML
    private TextField dniTF;
    @FXML
    private Label title;
    @FXML
    private Label instruction;
    
    String dni;
    private static PatientManagerInterface pmi;
    private static Database.DBManagerInterface dbman;
    private static BufferedReader br;
    private  PatientManager pm;
        
            public void start(Stage primaryStage) throws Exception {

           Parent root = FXMLLoader.load(getClass().getResource("AccessInformation.fxml"));

           
           Scene scene = new Scene(root);

           primaryStage.setTitle("ACCESS INFORMATION");
           primaryStage.setScene(scene);
           primaryStage.show();

       }

         

        
             
                      @FXML
        public void gobackGetStarted (ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("GetStarted.fxml"));

            Scene loginScene = new Scene(root);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(loginScene);
            window.show();
        }
        
        
                    @FXML
        public void searchByDNI (ActionEvent event) throws IOException {
            
            dni=dniTF.getText();
            Patient pat=pmi.searchSpecificPatientByDNI(dni);
            if (pat !=null){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("PatientsInformation.fxml"));
                Parent root = loader.load();
                Scene loginScene = new Scene(root);

                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                PatientsInformationController controller = loader.getController();
                controller.showPatient(pat);
                        
                window.setScene(loginScene);
                window.show();
            
            } else{
                
                Parent root = FXMLLoader.load(getClass().getResource("PatientsInformationError.fxml"));
                Scene loginScene = new Scene(root);

                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

                window.setScene(loginScene);
                window.show();
            }
             
            }
            
        
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
