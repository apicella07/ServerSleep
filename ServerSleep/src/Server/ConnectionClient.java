/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
/**
 *
 * @author marin
 */
public class ConnectionClient  {
    /*public static Patient receivePatient() throws ParseException {
        
        InputStream is = null;
        ServerSocket serversocket = null;
        Socket socket = null;
        BufferedReader buf = null;
        InputStreamReader ins = null;
        Patient pat = null;
        SimpleDateFormat formato=null;
        try {
            serversocket = new ServerSocket(9010);
            socket = serversocket.accept();
            is = socket.getInputStream();
            System.out.println("Connection established from the address" + socket.getInetAddress());
            ins = new InputStreamReader(socket.getInputStream());
            buf = new BufferedReader(ins);
            //Tengo que leer: name,lastname,telep,addres,dni y gender
            String line;
            String name, lastname, telephone, address, dni, gender;
            java.util.Date dob;
            while ((line = buf.readLine()) != null) {
                if (line.toLowerCase().contains("finish")) {
                    System.out.println("Stopping the server.");
                    releaseResources(buf, socket, serversocket);
                    System.exit(0);
                }
                name = line;
                lastname = buf.readLine();
                telephone = buf.readLine();
                address = buf.readLine();
                formato= new SimpleDateFormat("yyyy-MM-dd");
                dob=formato.parse(buf.readLine());
                dni = buf.readLine();
                gender = buf.readLine();
                pat = new Patient(name, lastname, telephone, address, dob,dni, gender);
                System.out.println(pat.toString());
            }
            
        } catch (IOException ex) {
            System.out.println("Not possible to start the server.");
            ex.printStackTrace();
        } finally {
            releaseResources(buf, socket, serversocket);
        }
        return pat;
    }*/
    
    public static void receivePatient() throws IOException{
        ServerSocket serverSocket = new ServerSocket(9010);
        try{
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ConnectionClientThreads(socket)).start();
                
            }
        }finally{
            releaseResources(serverSocket);
        }
    }
    
    
    
    public static Report receiveReport() throws ParseException {
        InputStream is = null;
        ServerSocket serversocket = null;
        Socket socket = null;
        BufferedReader buf = null;
        InputStreamReader ins = null;
        Report rep = null;
        SimpleDateFormat formato=null;
        try {
            serversocket = new ServerSocket(9010); 
            socket = serversocket.accept();
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
                    releaseResources(buf, socket, serversocket);
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
        } finally {
            releaseResources(buf, socket, serversocket);
        }
        return rep;
    }
    
    public static FileWriter receiveFile() {
        int byteRead;
        FileWriter file1=null;
        ServerSocket serverSocket=null;
        BufferedWriter buf=null;
        try {
                serverSocket = new ServerSocket(9010);
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                file1=new FileWriter("./PatientsFile.txt");
                buf=new BufferedWriter(file1);
                while ((byteRead = inputStream.read()) != -1) {
                    char caracter = (char) byteRead;
                    System.out.print(caracter);
                    buf.write(caracter+"\t");
                }
                buf.flush();
                System.out.println("File was succesfully received.");
            } catch (IOException ex) {
                System.out.println("Not possible to start the server.");
                Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            releaseResourcesServer(buf,serverSocket);
        }
        return file1;
    }
    
    
    private static void releaseResources(ServerSocket serverSocket){
       try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    private static void releaseResources(BufferedReader bu, Socket socket, ServerSocket serverSocket) {

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

        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static void releaseResourcesServer(BufferedWriter buf,ServerSocket serverSocket) {
        try {
            buf.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) throws IOException {
        // ClassNotFoundException, ParseException, UnknownHostException
        receivePatient();
        //receiveReport();
        
    }


}

