/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;
/**
 *
 * @author marin
 */
public class ConnectionClient  {
    private static BufferedReader br;
     
    private static int max;
    private static int num,numUsing;
    private static boolean inUse;
   // no se usa --?  private static String answer;
    
    private static boolean s1,s2,s3;
    
    public static void main(String[] args) throws IOException {
        receivePatient();
        
            }
    
    private static void releaseResources(ServerSocket serverSocket){
       try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     public static void pressEnter() {
		System.out.println("Press enter to go to the main menu and continue.");
		try {
			String nothing;
			nothing = br.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
     public static int requestNumber(int max) {
		// int max is the maximum option that is acceptable
		 br = new BufferedReader(new InputStreamReader(System.in));
                int num;
		do {

			num = ui.takeInteger(br, "Introduce the number: ");

		} while (ui.CheckOption(num, max));

		return num;
	}
     public static void receivePatient() throws IOException{
        ServerSocket serverSocket = new ServerSocket(9010);
        try{
            while (true) {
                //Socket socket = serverSocket.accept();
                int max;
                int num,numUsing;
                boolean inUse;
                String answer;
                br = new BufferedReader(new InputStreamReader(System.in));
                boolean s1 = true,s2 = true,s3 = true;
                
                // SET OF SOCKETS OPEN
                
                System.out.println("MENU SERVER TO SELECT CONNECTION");
                while(true){
                    System.out.println("\nWhat do you want to do?\n"+"1.Receive patient.\n"+"2.Receive Report.\n"+"3.Receive File.\n");
                    max=3;
                
                    System.out.println("0. Exit.\n");
                    num=requestNumber(max);
                    numUsing=num;
                    //num=Integer.parseInt(reader.readLine());
                    inUse=true;
                
                    while(inUse){
                    switch(num){
                        case 1:
                            Socket socket1 = serverSocket.accept();
                            new Thread(new ConnectionClientThreads(socket1)).start();
                            s1= socket1.isClosed();
                            break;
                        case 2:
                            Socket socket2 = serverSocket.accept();
                            new Thread(new ConnectionReportThreads(socket2)).start();
                            s2= socket2.isClosed();
                            break;
                        case 3:
                            Socket socket3 = serverSocket.accept();
                            new Thread(new ConnectionFileThreads(socket3)).start();
                            s3= socket3.isClosed();
                            break;
                
                        default:
                            inUse=false;
                            if(s1==true & s2==true & s3 ==true){
                                System.out.println("Closing the server");
                                releaseResources(serverSocket);   
                            }
                            else{
                                System.out.println("The server can not be closed because the open sockets");
                                
                            }
                            System.exit(0);
                            break;
                    }
                    }
                // SI ES 4 O CUALQUIER COSA PARA CERRAR 
                // VERIFICO QUE EL SET DE CONEXIONES (SOCKETS) TIENE TODOS LOS SOCKETS CERRADOS
                }
            }
        }finally{
            releaseResources(serverSocket);
        }
    }
}

