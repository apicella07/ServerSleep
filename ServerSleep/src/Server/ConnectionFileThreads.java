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
            flwriter = new FileWriter("./PatientFile.txt"); 
            boolean stopClient = false;
            bfwriter = new BufferedWriter(flwriter);
            inputStream = socket.getInputStream();
            while (!stopClient) {
                byteRead = inputStream.read();
                char caracter = (char) byteRead;
                bfwriter.write(caracter);
                if (byteRead == -1 || byteRead == 'x') {
                    System.out.println("Character reception finished");
                    stopClient = true;
                }
                System.out.print(caracter);
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
