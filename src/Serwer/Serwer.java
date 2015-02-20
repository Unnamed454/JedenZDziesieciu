package Serwer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

import Polecenia.Aktualizacja;
import Polecenia.Polecenie;
import Serwer.strategia.*;
import Serwer.iterator.MIterator;
import Serwer.obserwator.Obserwowany;

public class Serwer implements Obserwowany{
	private ServerSocket server;
    private int port = 9633;
    private Socket socket;
    private Strategia etap;
    private Gracze kolekcjaGraczy = new Gracze();
	private Vector<Integer[]> tablicaWynikow = new Vector<Integer[]>(10);

    public Serwer() throws IOException{
        server = new ServerSocket(port);
        server.setReuseAddress(true);
    }
    
	public void inicjalizujTabliceWynikow(){
		for(int i = 0; i < 10; i++){
			tablicaWynikow.add(new Integer[]{3,0});
		}
	}
    
    public GraczKomunikacja getGracz(int id){
    	return kolekcjaGraczy.get(id-1);
    }
    
    public int ileGra(){
    	int ile = 0;
    	MIterator it = kolekcjaGraczy.getIterator();
    	
    	while(it.hasNext()){
    		GraczKomunikacja k = (GraczKomunikacja) it.next();
			if(k.czyGra() == true) ile++;
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
		Polecenie polecenie = new Aktualizacja();
		polecenie.ustawObiekt(tablicaWynikow);
		
		MIterator it = kolekcjaGraczy.getIterator();
		
		while(it.hasNext()){
			GraczKomunikacja k = (GraczKomunikacja) it.next();
			try{
				k.wyslij(polecenie);
			}catch (Exception e) {
				System.out.println("Wysylanie tablicy - jakis blad!");
			}
		}
	}

	public void dodajObserwatora(GraczKomunikacja komunikacja){
			kolekcjaGraczy.add(komunikacja);	
	}
	
	public void usunObserwatora(int index){
		kolekcjaGraczy.set(index, null);
	}
    
	public Integer[] getWynik(int id){
		return tablicaWynikow.get(id-1);
	}
	
	public void setWynik(int id, String dodac_odjac, int punkty){
		switch(dodac_odjac){
			case "dodac":
				tablicaWynikow.get(id-1)[1] += punkty;
				break;
				
			case "odjac":
				if(--tablicaWynikow.get(id-1)[0] == 0)
					kolekcjaGraczy.get(id-1).setJuzNiegra();
				break;
		}
	}
	
    public void zbierzGraczy() throws IOException, InterruptedException, ClassNotFoundException{
	    System.out.println("Waiting for client message...");
	    MIterator it = kolekcjaGraczy.getIterator();
	    
	    while(it.hasNext()){
	            socket = server.accept();
	            dodajObserwatora(new GraczKomunikacja(socket, it.getIndex()));
	            System.out.println(it.getIndex()+1);
	            it.next();
	    }
    }
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{
        Serwer serwer = new Serwer();
        serwer.inicjalizujTabliceWynikow();
        serwer.zbierzGraczy();

	    serwer.ustawEtap("Etap_I");
	    serwer.graj();
	    
	    serwer.ustawEtap("Etap_II");
	    serwer.graj();
	    
	    serwer.ustawEtap("Etap_III");
	    serwer.graj();
    }
}