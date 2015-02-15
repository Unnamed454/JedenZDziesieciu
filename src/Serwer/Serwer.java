package Serwer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Serwer.strategia.*;
import Serwer.obserwator.Obserwowany;

public class Serwer implements Obserwowany{
	private ServerSocket server;
    private int port = 9308;
    private Socket socket;
    private Strategia etap;
    GraczKomunikacja tablicaGraczy[] = new GraczKomunikacja[10];
    
    public Serwer() throws IOException{
            server = new ServerSocket(port);
            server.setReuseAddress(true);
    }
    
    public void ustawEtap(String etap){
    	switch(etap){
	    	case "Etap_I":
	    		this.etap = new EtapI();
	    		break;
	    	case "Etap_II":
	    		this.etap = new EtapII();
	    		break;
	    	case "Etap_III":
	    		this.etap = new EtapIII();
	    		break;
    	}
    }
    
    public void graj(){
    	etap.graj();
    }
    
	public void powiadamiaj(){
		for(GraczKomunikacja k : tablicaGraczy){
			try {
				k.wyslij("");
			} catch (IOException e) {
				System.out.println("Wysylanie tablicy - jakis blad!");
			}
		}
		
	}

	public void dodajObserwatora() {
		// TODO Auto-generated method stub
		
	}

	public void usunObserwatora() {
		// TODO Auto-generated method stub
		
	}
    
    public void zbierzGraczy() throws IOException, InterruptedException, ClassNotFoundException{
	    System.out.println("Waiting for client message...");

	    
	    for(int i=0; i<10; i++) {
	            socket = server.accept();
	            tablicaGraczy[i] = new GraczKomunikacja(socket,i);
	            System.out.println(i);
	    }
    }
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{
        Serwer serwer = new Serwer();
        serwer.zbierzGraczy();

	    serwer.ustawEtap("Etap_I");
	    serwer.graj();
    }
}