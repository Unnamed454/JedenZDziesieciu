package Gracz;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Gracz{
	private StanGracza stan;
	
	private String nazwaHosta;
	private int port;
	
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
	
	public void polacz() throws UnknownHostException, IOException{
        System.out.println("£¹czenie do "+ nazwaHosta + ":" + port);
        gniazdo = new Socket(nazwaHosta, port);
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
			if(gracz.podajStan() == new Czeka()){
				
			}
			else if(gracz.podajStan() == new Odpowiada()){
				gracz.wyslijOdpowiedz();
			}
		}
	}
}
