
package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EEG implements Serializable {
     InputStream inputStream=null;
     PrintWriter printWriter=null;
     BufferedReader bufferedReader=null;
     Socket socket=null;
     ArrayList<Integer> eegValues = new ArrayList<Integer>();
     
     private String dni;
     private Date eegDate;
     private Object file; //NO ES PARA QUE SE QUEDE ASÍ TIENE QUE SER UN FILE!?

    public Object getFile() {
        return file;
    }
     
     public EEG(ArrayList<Integer> eegVals){
         this.eegValues=eegVals;
     }

    public String getDni() {
        return dni;
    }

    public Date getEegDate() {
        return eegDate;
    }
     public EEG(Date eegdate,String dni,Object file){
         super();
         this.dni=dni;
         this.eegDate=eegdate;
         
     }
     
     public EEG(){
         super();
     }
     
      public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ArrayList<Integer> getEegValues() {
        return eegValues;
    }
     
     
     
     
     
     
     
     
     
     
     
    //esto es donde el bitalino va a guardar los resulrados 
    // En el report poner como id la fecha y así la persona puede decidir que hemograma ver y coger el que quiere 
 //Lo que escribo sería cómo el paciente envía los EEG:    //esto es donde el bitalino va a guardar los resulrados 
    // En el report poner como id la fecha y así la persona puede decidir que hemograma ver y coger el que quiere 
 //Lo que escribo sería cómo el paciente envía los EEG:
/*
        try {
            socket=new Socket("localhost",9000);
            inputStream = socket.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(EEG.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       
  
    try {

    } catch(IOException ex) {
        System.out.println("Unable to send the EEG values.");
        Logger.getLogger(EEG.class.getName().log(level.SEVERE,null,ex));
    } finally {
        releaseResources(inputStream, socket);
    }
    */

   
}
