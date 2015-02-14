package Gracz;

import java.io.*;
import java.net.*;

public class Gracz implements Obserwator{
	private StanGracza stan;
	
	private String nazwaHosta;
	private int port;
	private int szanse = 3;
	private int etap = 1;
	//private int punkty = 0;
	
	Socket gniazdo;
	
	Gracz(int port){
		this.port = port;
	}
	
	@Override
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
	
	public void polacz() throws UnknownHostException, IOException{
        InetAddress host = InetAddress.getLocalHost();
        gniazdo = new Socket(host.getHostName(), port);
        ustalStan(new Czeka());
        System.out.println("Do³¹czono do gry!");
	}
	
	public int getEtap(){return etap;};
	
	public int getSzanse() {return szanse;}
	
	public void odlacz(){
		
	}
	
	public static void main(String arg[]) throws UnknownHostException, IOException, ClassNotFoundException{
		int port = 930;
		
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
