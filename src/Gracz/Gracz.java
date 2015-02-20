package Gracz;

import Gracz.stan.*;

import java.io.*;
import java.net.*;

public class Gracz implements Obserwator{
	private StanGracza stan;
	
	private int port;
	private int id;
	private int tablicaWynikow[][] = {{3,0},{3,0},{3,0},{3,0},{3,0},{3,0},{3,0},{3,0},{3,0},{3,0}};
	
	Socket gniazdo;
	
	Gracz(int port){
		this.port = port;
	}
	
	public void wyswietlWyniki(){
		System.out.println("Dotychczasowe wyniki:\n");
		
		for(int i = 0; i < 10; i++){
			System.out.println("Gracz nr " + Integer.toString(i + 1) + " -\tSzanse: " + Integer.toString(tablicaWynikow[i][0]));
		}
	}
	
	public void update(int[][] integers){
		tablicaWynikow = new int[integers.length][];
		for(int i = 0; i < integers.length; i++)
			tablicaWynikow[i] = integers[i].clone();
	}
	
	public Socket getGniazdo(){
		return gniazdo;
	}
	
	public void dzialaj(){
		stan.graj(this);
	}
	
	public void ustalStan(StanGracza stan){
		this.stan = stan;
	}
	
	
	public void polacz() throws UnknownHostException, IOException, ClassNotFoundException{
        InetAddress host = InetAddress.getLocalHost();
        gniazdo = new Socket(host.getHostName(), port);

        ObjectInputStream ois = new ObjectInputStream(gniazdo.getInputStream());
        this.id = (Integer)ois.readObject();
         
        System.out.println("Do³¹czono do gry! Twoje id: " + Integer.toString(this.id));
        ustalStan(new Czeka());
    }
	
	public void odlacz(){
		
	}
	
	public int getID(){
		return id;
	}
	
	public static void main(String arg[]) throws UnknownHostException, IOException, ClassNotFoundException{
		int port = 9630;
		
		Gracz gracz = new Gracz(port);
		gracz.polacz();
		
		while(true){
			gracz.dzialaj();
		}
	}
}
