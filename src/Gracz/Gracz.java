package Gracz;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Gracz{
	private StanGracza stan;
	
	private String nazwaHosta;
	private int port;
	private int szanse = 3;
	private int etap = 1;
	private int punkty = 0;
	
	Socket gniazdo;
	
	Gracz(int port){
		this.port = port;
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
	
	public void wyslijOdpowiedz() throws IOException, ClassNotFoundException{
		Scanner scanner = new Scanner(System.in);
		String odpowiedz = scanner.nextLine();
		scanner.close();
		
		new ObjectOutputStream(gniazdo.getOutputStream()).writeObject(odpowiedz);
		
		String odSerwera = (String) new ObjectInputStream(gniazdo.getInputStream()).readObject();

		if(odSerwera.equals("Y")) {
			if(etap == 1) ustalStan(new Czeka());
			else if(etap == 2) ustalStan(new Wyznacza());
			else if(etap == 3){
				ustalStan(new Wyznacza());
				punkty += 10;
			}
		}
		else if(odSerwera.equals("N")) {
			if(szanse != 0) {
				zmniejszSzanse();
				ustalStan(new Czeka());
			}
			else ustalStan(new Przegral());
		}
	}
	
	public void czekaj() throws IOException, ClassNotFoundException {
		String odSerwera = (String) new ObjectInputStream(gniazdo.getInputStream()).readObject();

		if(odSerwera.equals("Y")) ustalStan(new Odpowiada());
		else if(odSerwera.equals("++")) etap++;
		else if(odSerwera.equals("N")) return;
	}
	
	public void wyznacz() throws IOException{ //ta odpowiedz musi byæ parsowana do inta - to bedzie nr od 1 do 10 ktory nastepny czlowieczek odpowiada
		
		Scanner scanner = new Scanner(System.in);
		String odpowiedz = scanner.nextLine();
		scanner.close();
		
		new ObjectOutputStream(gniazdo.getOutputStream()).writeObject(odpowiedz);
		ustalStan(new Czeka());
	}
	
	public void odlacz(){
		
	}
	
	public static void main(String arg[]) throws UnknownHostException, IOException, ClassNotFoundException{
		int port = 930;
		
		Gracz gracz = new Gracz(port);
		gracz.polacz();
		
		while(true){
			if(gracz.podajStan() instanceof Czeka){
				gracz.czekaj();
			}
			else if(gracz.podajStan() instanceof Odpowiada){
				gracz.wyslijOdpowiedz();
			}
			else if(gracz.podajStan() instanceof Wyznacza){
				gracz.wyznacz();
			}
			else if(gracz.podajStan() instanceof Przegral){
				gracz.odlacz();
			}
		}
	}
}
