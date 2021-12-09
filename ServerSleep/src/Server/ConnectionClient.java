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
    
    public static void receivePatient() throws IOException{
        ServerSocket serverSocket = new ServerSocket(9010);
        try{
            while (true) {
                Socket socket = serverSocket.accept();
                
                
                // SET OF SOCKETS OPEN
                
                // desplegable que elija la accion y segun la accion ejecuto un thread u otro 
                // RECIBO INT CON LA OPCION DEL USUARIO
                // SI ES 1
                new Thread(new ConnectionClientThreads(socket)).start();
                // SI ES 2
                // OTRO THREAD
                
                // SI ES 3
                // OTRO THREAD
                
                // SI ES 4 O CUALQUIER COSA PARA CERRAR 
                // VERIFICO QUE EL SET DE CONEXIONES (SOCKETS) TIENE TODOS LOS SOCKETS CERRADOS
                
            }
        }finally{
            releaseResources(serverSocket);
        }
    }
    
    public static void receiveReport() throws IOException{
        ServerSocket serverSocket = new ServerSocket(9010);
        try{
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ConnectionReportThreads(socket)).start();
                
            }
        }finally{
            releaseResources(serverSocket);
        }
    }
    
    public static void receiveFile() throws IOException{
        int byteRead;
        ServerSocket serverSocket = new ServerSocket(9010);
        try{
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();

                while ((byteRead = inputStream.read()) != -1) {
                    char caracter = (char) byteRead;
                    System.out.print(caracter);
                }
                
            }
        }finally{
            releaseResources(serverSocket);
        }
    }
    
    private static void releaseResources(ServerSocket serverSocket){
       try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static void main(String[] args) throws IOException {
        // ClassNotFoundException, ParseException, UnknownHostException
        
        receiveFile();
        
    }


}

