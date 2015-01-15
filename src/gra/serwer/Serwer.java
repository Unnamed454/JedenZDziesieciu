package gra.serwer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import gra.Statystyki;

public class Serwer implements Obserwowany{
	private ServerSocket server;
    private int port = 930;
    private Socket socket;
    private Vector<Socket> obserwatorzy = new Vector<Socket>(10);
    private Statystyki tablicaWynikow[] = new Statystyki[10];
    private int pytany = 0;
    
    public Serwer() throws IOException{
            server = new ServerSocket(port);
            server.setReuseAddress(true);
    }
 
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Serwer example = new Serwer();
        example.zbierzGraczy();
    }
    
    public void dodajObserwatora(Socket socket){
    	obserwatorzy.add(socket);
    }
    
    public void usunObserwatora(int pozycjaGracza){
    	obserwatorzy.set(pozycjaGracza, null);
    }
    
    
    
    public void zadajPytanie(Socket socket, String pytanie){}
    
    
    
    public void informujObserwatorow() throws IOException{
    	int i = 0;
    	
    	for(Socket sock : obserwatorzy){
    		new ObjectOutputStream(sock.getOutputStream()).writeObject(tablicaWynikow[i]);
    		i++;
    	}
    }
    
    public void zbierzGraczy() throws IOException, InterruptedException, ClassNotFoundException{
	    System.out.println("Czekam na klientów...");

	    Socket[] tablicaGraczy = new Socket[10];
	    for(int i=0; i<10; i++) {
	            socket = server.accept();
	            tablicaGraczy[i] = socket;
	            dodajObserwatora(socket);
	            System.out.println(i + ": Jest");
	    }
	    System.out.println("Mamy wszystkich graczy, Jazda!");
	    
	    new ObjectOutputStream(tablicaGraczy[pytany].getOutputStream()).writeObject("Y:Tu pytanie");
	    String odGracza = (String) new ObjectInputStream(tablicaGraczy[pytany].getInputStream()).readObject();
	   
    	//sprawdz czy odGracza to poprawna odp ? jakas twoja metoda sprawdzajaca czy poprawna odpowiedz gracza
    	if(true){
    		new ObjectOutputStream(tablicaGraczy[pytany].getOutputStream()).writeObject("P");
    	}
    	else{
    		new ObjectOutputStream(tablicaGraczy[pytany].getOutputStream()).writeObject("NP");
    		
    		if(pytany == 9) pytany = 0;
    	    else pytany++;
    	}
	    
	    
	    
	    
	    
	    
    }
}
 


