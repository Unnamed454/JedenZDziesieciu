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
    private GraczKomunikacja tablicaGraczy[] = new GraczKomunikacja[10];
	private int tablicaWynikow[][] = new int[10][2];

    public Serwer() throws IOException{
            server = new ServerSocket(port);
            server.setReuseAddress(true);
    }
    
    public GraczKomunikacja getGracz(int id){
    	return tablicaGraczy[--id];
    }
    
    public int ileGra(){
    	int ile = 0;
    	
    	for(GraczKomunikacja k : tablicaGraczy){
			if(k.czyGra() == true) ile += 1;
		}
    	return ile;
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
    	etap.graj(this);
    }
    
	public void powiadamiaj(){
		for(GraczKomunikacja k : tablicaGraczy){
			try {
				k.wyslij(tablicaWynikow);
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
    
	public int[] getWynik(int id){
		return tablicaWynikow[--id];
	}
	
	public void setWynik(int id, String dodac_odjac, int punkty){
		switch(dodac_odjac){
		case "dodac":
			tablicaWynikow[--id][1] += punkty;
			break;
		case "odjac":
			tablicaWynikow[--id][0] -= 1;
			if(tablicaWynikow[id][0] == 0)
				getGracz(id).setJuzNiegra();
			break;
		}
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
	    
	    serwer.ustawEtap("Etap_II");
	    serwer.graj();
	    
	    serwer.ustawEtap("Etap_III");
	    serwer.graj();
    }
}