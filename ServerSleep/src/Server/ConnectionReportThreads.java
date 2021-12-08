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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmsan
 */
public class ConnectionReportThreads implements Runnable{
 
    Socket socket;
    
    public ConnectionReportThreads(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run(){
        InputStream is = null;
        BufferedReader buf = null;
        InputStreamReader ins = null;
        Report rep = null;
        SimpleDateFormat formato=null;
        try {
            is = socket.getInputStream();
            System.out.println("Connection established from the address" + socket.getInetAddress());
            ins = new InputStreamReader(socket.getInputStream());
            buf = new BufferedReader(ins);
            String line;
            String dni,sleepqual,exhaus,average,movement,timeToFall,rest,stayAwake,timesAwake,dreams,worries,todaysMood,doubtsForDoctor;
            Date todaysDate;
            while ((line = buf.readLine()) != null) {
                if (line.toLowerCase().contains("finish")) {
                    System.out.println("Stopping the server.");
                    releaseResources(is, ins, buf, socket);
                    System.exit(0);
                }
                dni=buf.readLine();
                formato= new SimpleDateFormat("yyyy-MM-dd");
                todaysDate=formato.parse(buf.readLine());
                sleepqual = buf.readLine();
                exhaus = buf.readLine();
                average = buf.readLine();
                movement = buf.readLine();
                timeToFall = buf.readLine();
                rest=buf.readLine();
                stayAwake=buf.readLine();
                timesAwake=buf.readLine();
                dreams=buf.readLine();
                worries=buf.readLine();
                todaysMood=buf.readLine();
                doubtsForDoctor=buf.readLine();
                rep=new Report(todaysDate,sleepqual,exhaus,average,movement,timeToFall,rest,stayAwake,timesAwake,dreams,worries,todaysMood,doubtsForDoctor);
                System.out.println(rep.toString());
            }
            
        } catch (IOException ex) {
            System.out.println("Not possible to start the server.");
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(ConnectionReportThreads.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             releaseResources(is, ins, buf, socket);
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
