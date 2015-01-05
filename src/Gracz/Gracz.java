package Gracz;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Gracz{
	private StanGracza stan;
	
	private String nazwaHosta;
	private int port;
	private int szanse = 3;
	
	Socket gniazdo;
	
	Gracz(String nazwaHosta, int port){
		this.nazwaHosta = nazwaHosta;
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
        System.out.println("£¹czenie do "+ nazwaHosta + ":" + port);
       // gniazdo = new Socket(nazwaHosta, port);
        ustalStan(new Odpowiada());
        System.out.println("Po³¹czono");   
	}
	
	public void wyslijOdpowiedz() throws IOException{
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(gniazdo.getOutputStream()));
		Scanner scanner = new Scanner(System.in);
		String odpowiedz = scanner.next();
		writer.write(odpowiedz);
		writer.newLine();
		writer.flush();
		scanner.close();
		
		String odSerwera;
		BufferedReader czytaj = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
		
		odSerwera = czytaj.readLine();
		if(odSerwera == "Y") ustalStan(new Wyznacza());
		else if(odSerwera == "N") {
			if(szanse != 0) {
				zmniejszSzanse();
				ustalStan(new Czeka());
			}
			else ustalStan(new Przegral());
		}
	}
	
	public void czekaj() throws IOException{
		String odSerwera;
		BufferedReader czytaj = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
		
		odSerwera = czytaj.readLine();
		
		if(odSerwera == "Y") ustalStan(new Odpowiada());
		else if(odSerwera == "N") return;
	}
	
	public void wyznacz() throws IOException{ //ta odpowiedz musi byæ parsowana do inta - to bedzie nr od 1 do 10 ktory nastepny czlowieczek odpowiada
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(gniazdo.getOutputStream()));
		Scanner scanner = new Scanner(System.in);
		String odpowiedz = scanner.next();
		writer.write(odpowiedz);
		writer.newLine();
		writer.flush();
		scanner.close();
		
		ustalStan(new Czeka());
	}
	
	public void odlacz(){
		
	}
	
	public static void main(String arg[]) throws UnknownHostException, IOException{
		Scanner scanner = new Scanner(System.in);
		String nazwaHosta = new String();
		int port = 9999;
		
		System.out.println("Podaj nazwê hosta: ");
		nazwaHosta = scanner.next();
		
		scanner.close();
		Gracz gracz = new Gracz(nazwaHosta, port);
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
