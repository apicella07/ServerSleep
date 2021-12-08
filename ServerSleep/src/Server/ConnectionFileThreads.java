/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmsan
 */
public class ConnectionFileThreads implements Runnable{
    int byteRead;
     Socket socket;
    
    public ConnectionFileThreads(Socket socket) {
        this.socket = socket;
    }
    
   @Override
    public void run (){
    InputStream inputStream = null;
    FileWriter flwriter = null;
    BufferedWriter bfwriter =null;
    
        try {
            flwriter = new FileWriter("./PatientFile.txt"); //esto se tiene que guardar en la base de datos para cada patient
        
            bfwriter = new BufferedWriter(flwriter);
            inputStream = socket.getInputStream();
            while ((byteRead = inputStream.read()) != -1) {
                char caracter = (char) byteRead;
                bfwriter.write(caracter);
               // System.out.print(caracter);
                //proyecto: escribir en un file distinto cada input de cada client 
            }            
            bfwriter.flush();
            System.out.println("File was succesfully received.");
        } catch (IOException ex) {
        System.out.println("Not possible to start the server.");
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            releaseResourcesFile(bfwriter,inputStream, flwriter ,socket);
        }
    }
    
    public static void main(String[] args) throws IOException {
        // ClassNotFoundException, ParseException, UnknownHostException
        
        
    }
    
    private static void releaseResourcesFile(BufferedWriter bu,InputStream inputStream,FileWriter flwriter , Socket socket) {
        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            flwriter.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bu.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
