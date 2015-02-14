package Gracz;

import java.io.*;
import java.net.*;

public class Gracz implements Obserwator{
	private StanGracza stan;
	
	private String nazwaHosta;
	private int port;
	private int szanse = 3;
	private int etap = 1;
	private int id;
	
	Socket gniazdo;
	
	Gracz(int port){
		this.port = port;
	}
	

	public void update(){
		
	}
	
	public void dzialaj(){
		stan.graj(this);
	}
	
	public StanGracza podajStan(){
		return stan;
	}
	
	public void ustalStan(StanGracza stan){
		this.stan = stan;
	}
	
	public void zmniejszSzanse(){
		szanse--;
	}
	
	public void polacz() throws UnknownHostException, IOException, ClassNotFoundException{
        InetAddress host = InetAddress.getLocalHost();
        gniazdo = new Socket(host.getHostName(), port);
      /*  
        Integer id_s = (Integer) new ObjectInputStream(gniazdo.getInputStream()).readObject();
        this.id = id_s.intValue();*/
        ObjectInputStream ois = new ObjectInputStream(gniazdo.getInputStream());
        Object a = ois.readObject();
       System.out.println(id);
        System.out.println("Do³¹czono do gry! Twoje id: " + Integer.toString(id));
        ustalStan(new Czeka());
    }
	
	public int getEtap(){return etap;};
	
	public int getSzanse() {return szanse;}
	
	public void odlacz(){
		
	}
	
	public static void main(String arg[]) throws UnknownHostException, IOException, ClassNotFoundException{
		int port = 9305;
		
		Gracz gracz = new Gracz(port);
		gracz.polacz();
		
		while(true){
			gracz.dzialaj();
//			if(gracz.podajStan() instanceof Czeka){
//				gracz.czekaj();
//			}
//			else if(gracz.podajStan() instanceof Odpowiada){
//				gracz.wyslijOdpowiedz();
//			}
//			else if(gracz.podajStan() instanceof Wyznacza){
//				gracz.wyznacz();
//			}
//			else if(gracz.podajStan() instanceof Przegral){
//				gracz.odlacz();
//			}
		}
	}
}
