package Serwer;

import java.io.IOException;
import Polecenia.Polecenie;
//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GraczKomunikacja {
    private Socket socket;
//    private int gotSomeData;
 
    public GraczKomunikacja(Socket socket,int id) throws IOException, ClassNotFoundException, InterruptedException {
        this.socket = socket;
        this.komunikuj(id);
    }
    
    public void wyslij(Polecenie polecenie) throws IOException{
    	new ObjectOutputStream(socket.getOutputStream()).writeObject(polecenie);
    }
 
    public void komunikuj(int id) throws IOException, InterruptedException, ClassNotFoundException{
        	wyslij(Integer.toString(++id));
        	
//    		gotSomeData = socket.getInputStream().available();
//    		if(gotSomeData > 0) {
//    			String odKlienta = (String) new ObjectInputStream(socket.getInputStream()).readObject();
//    		}
    	}
    }