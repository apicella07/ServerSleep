/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversleepapp;

import Database.PatientManager;
import Database.PatientManagerInterface;
import Server.Patient;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 99lorena
 */
public class PatientsInformationController implements Initializable {
     @FXML private TextField Answer1;
     String answer1;
    /**
     * Initializes the controller class.
     */
    
        //declaramos botones
            @FXML 
        private Button back;
    
               @FXML
        private Label title;
               
            @FXML 
        private Label label1;
            
            private static PatientManagerInterface pmi;
            private static Database.DBManagerInterface dbman;
            private static BufferedReader br;
            private  PatientManager pm;
            
            private Patient selectedpatient;
            
         public void start(Stage primaryStage) throws Exception {

           Parent root = FXMLLoader.load(getClass().getResource("PatientsInformation.fxml"));
           
           Patient pat= new Patient(); 
           label1.setText(pat.toString());
           Scene scene = new Scene(root);
  
           primaryStage.setScene(scene);
           primaryStage.show();
           

       }
         
                    @FXML
           public void showPatient (Patient patient){
               selectedpatient= patient;
               label1.setText(selectedpatient.toString());
           }
           
 
                        @FXML
           public void goNext (ActionEvent event) throws IOException{
               Parent root = FXMLLoader.load(getClass().getResource("MenuOptionsServer.fxml"));

               Scene loginScene = new Scene(root);

               Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

               window.setScene(loginScene);
               window.show();
           }
            
           
                        @FXML
           public void goBackMenuOptions (ActionEvent event) throws IOException{
               Parent root = FXMLLoader.load(getClass().getResource("AccessInformation.fxml"));

               Scene loginScene = new Scene(root);

               Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

               window.setScene(loginScene);
               window.show();
           }
    

            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //PARA INICIALIZAR LAS COLUMNAS 

    }    
    
}


