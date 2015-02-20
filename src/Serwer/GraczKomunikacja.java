package Serwer;

import java.io.IOException;
import java.io.ObjectInputStream;

import Polecenia.Polecenie;


//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GraczKomunikacja {
    private Socket socket;
    private boolean gra = true;
//    private int gotSomeData;
 
    public String getWiadomoscZGniazda(){
    	String ret = null;
    	
		try {
			ret = (String) new ObjectInputStream(socket.getInputStream()).readObject();
		}
		catch (Exception e) {
			System.out.println("Blad - get Wiadomosc z gniazda");
		}
		if(ret == null) System.out.println("Tak narazie prewencyjnie!");
		return ret;
    }
    
    public boolean czyGra(){
    	return gra;
    }
    
    public void setJuzNiegra(){
    	gra = false;
    }
    
    public GraczKomunikacja(Socket socket, int id) throws IOException, ClassNotFoundException, InterruptedException {
        this.socket = socket;
        this.komunikuj(id);
    }
    
    public void wyslij(Polecenie polecenie) throws IOException{
    	try{
    		new ObjectOutputStream(socket.getOutputStream()).writeObject(polecenie);
    	}
    	catch(Exception e ){
    		System.out.println("co sie dzieje!");
    	}
    }
 
    public void komunikuj(int id) throws IOException, InterruptedException, ClassNotFoundException{
    		new ObjectOutputStream(socket.getOutputStream()).writeObject(++id); //wyslanie graczowi jako pierwszej informacji jako ktory gracz zostal podlaczony(jego id)
        	
//    		gotSomeData = socket.getInputStream().available();
//    		if(gotSomeData > 0) {
//    			String odKlienta = (String) new ObjectInputStream(socket.getInputStream()).readObject();
//    		}
    	}
    }