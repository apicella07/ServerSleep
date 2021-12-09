/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmsan
 */
public class ConnectionClientThreads implements Runnable{
    Socket socket;
    
    public ConnectionClientThreads(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run(){
        InputStream is = null;
        BufferedReader buf = null;
        InputStreamReader ins = null;
        try {
            is = socket.getInputStream();
            System.out.println("Connection established from the address" + socket.getInetAddress()+"\n");
            ins = new InputStreamReader(socket.getInputStream());
            buf = new BufferedReader(ins);
            String line;
            while ((line = buf.readLine()) != null) {
                if (line.toLowerCase().contains("finish")) {
                    System.out.println("Stopping the server.");
                    releaseResources(is,ins,buf, socket);
                    System.exit(0);
                }
                System.out.println(line);
            }
            System.out.println("Patient was succesfully received.");
            
        } catch (IOException ex) {
            System.out.println("Not possible to start the server.");
            ex.printStackTrace();
        } finally {
            releaseResources(is, ins, buf, socket);//cerramos socket al terminar
        }
    }
    
     private static void releaseResources(InputStream i, InputStreamReader in,BufferedReader bu, Socket socket) {

        try {
            i.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            in.close();
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
