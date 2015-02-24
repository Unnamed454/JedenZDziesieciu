package Gracz;

import Gracz.stan.*;
import Polecenia.Aktualizacja;
import Polecenia.Czekasz;
import Polecenia.Odpadasz;
import Polecenia.Odpowiadasz;
import Polecenia.Polecenie;
import Polecenia.Wyznaczasz;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Gracz implements Obserwator{
	private StanGracza stan;
	private int port;
	private int id;
	private Vector<Integer[]> tablicaWynikow= new Vector<Integer[]>(10);
	private Scanner scanner = new Scanner(System.in);
	
	public class Czeka implements StanGracza{
		@SuppressWarnings("unchecked")
		public void graj(){
			try{
				Polecenie<?> odSerwera = (Polecenie<?>) new ObjectInputStream(getGniazdo().getInputStream()).readObject();
				
				if(odSerwera instanceof Czekasz) ustalStan(new Czeka());
				if(odSerwera instanceof Odpowiadasz){
					System.out.println("Odpowiadasz na pytanie: " + (String)odSerwera.zwrocPowiazanyObiekt());
					ustalStan(new Odpowiada());
				}
				if(odSerwera instanceof Odpadasz) ustalStan(new Przegral());
				if(odSerwera instanceof Wyznaczasz) {
					System.out.println((String)odSerwera.zwrocPowiazanyObiekt());
					ustalStan(new Wyznacza());
				}
				if(odSerwera instanceof Aktualizacja){
					update((Vector<Integer[]>) odSerwera.zwrocPowiazanyObiekt());
					ustalStan(new Czeka());
				}
			}
			catch(Exception e){
				System.out.println("B��d przy odczytywaniu!");
			}
		}
	}
	
	public class Odpowiada implements StanGracza{
		public void graj(){
			wyswietlWyniki();
			
			String odpowiedz = scanner.nextLine();
			
			try{
				new ObjectOutputStream(getGniazdo().getOutputStream()).writeObject(odpowiedz);
			}
			catch(Exception e){
				System.out.println("B��d przy Odpowiadaniu!");
			}
			
			ustalStan(new Czeka());
		}
	}
	
	public class Wyznacza implements StanGracza{
		public void graj(){
			int nastepny;
			
			do{
				System.out.println("Podaj nastepnego odpowiadaj�cego(1-10) !nie mo�na wskaza� siebie!");
				
				
				String odpowiedz = scanner.nextLine();
				
				nastepny = Integer.valueOf(odpowiedz);
			}while(nastepny <= 0 && nastepny > 10 || nastepny == getID());
			
			try{
				new ObjectOutputStream(getGniazdo().getOutputStream()).writeObject(Integer.toString(nastepny));
			}
			catch(Exception e){
				System.out.println("B��d przy przesy�aniu odpowiedzi!");
			}
			
			ustalStan(new Czeka());
		}
	}
	
	public class Update implements StanGracza{
		public void graj(){
			try {
				@SuppressWarnings("unchecked")
				Polecenie<Vector<Integer[]>> odSerwera = (Polecenie<Vector<Integer[]>>) new ObjectInputStream(getGniazdo().getInputStream()).readObject();
				update((Vector<Integer[]>) odSerwera.zwrocPowiazanyObiekt());
			}
			catch (Exception e) {
				System.out.println("Blad przy odbieraniu tablicy wynikow!");
			}
			ustalStan(new Czeka());
		}
	}
	public class Przegral implements StanGracza{
		public void graj(){
			odlacz();
		}
	}
	
	Socket gniazdo;
	
	Gracz(int port){
		this.port = port;
	}
	
	public void inicjalizujTabliceWynikow(){
		for(int i = 0; i < 10; i++){
			tablicaWynikow.add(new Integer[]{3,0});
		}
	}
	
	public void wyswietlWyniki(){
		Iterator<Integer[]> it = tablicaWynikow.iterator();
		int i = 0;
		
		System.out.println("Dotychczasowe wyniki:\n");
		
		while(it.hasNext()){
			System.out.println("Gracz nr " + Integer.toString(i+1) + " -\tSzanse: " + Integer.toString(tablicaWynikow.get(i)[0]));
			i++;
			it.next();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void update(Vector<Integer[]> tablica){
		this.tablicaWynikow = (Vector<Integer[]>) tablica.clone();
	}
	
	public Socket getGniazdo(){
		return gniazdo;
	}
	
	public void ustalStan(StanGracza stan){
		this.stan = stan;
	}
	
	public void polacz() throws UnknownHostException, IOException, ClassNotFoundException{
        InetAddress host = InetAddress.getLocalHost();
        gniazdo = new Socket(host.getHostName(), port);

        ObjectInputStream ois = new ObjectInputStream(gniazdo.getInputStream());
        this.id = (Integer)ois.readObject();
         
        System.out.println("Do��czono do gry! Twoje id: " + Integer.toString(this.id));
        ustalStan(new Czeka());
    }
	
	public void odlacz(){
		System.out.println("konczymy gre - wynik:");
		wyswietlWyniki();
		System.out.println("BYE!");
		scanner.close();
	}
	
	public int getID(){
		return id;
	}
	
	public static void main(String arg[]) throws UnknownHostException, IOException, ClassNotFoundException{
		int port = 9633;
		
		Gracz gracz = new Gracz(port);
		gracz.inicjalizujTabliceWynikow();
		gracz.polacz();
		
		while(true){
			gracz.stan.graj();
		}
	}
}
