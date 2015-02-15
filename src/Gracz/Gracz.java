package Gracz;

import Gracz.stan.*;

import java.io.*;
import java.net.*;

public class Gracz implements Obserwator{
	private StanGracza stan;
	
	private int port;
	private int szanse = 3;
	private int etap = 1;
	private int id;
	private int tablicaWynikow[][];
	
	Socket gniazdo;
	
	Gracz(int port){
		this.port = port;
	}

	public void update(){
		
	}
	
	public void aktualizujTablice(int[][] nowa){
		tablicaWynikow = new int[nowa.length][];
		for(int i = 0; i < nowa.length; i++)
			tablicaWynikow[i] = nowa[i].clone();
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
        this.id = Integer.valueOf((String)  ois.readObject());
         
        System.out.println("Do³¹czono do gry! Twoje id: " + Integer.toString(this.id));
        ustalStan(new Czeka());
    }
	
	public int getEtap(){return etap;};
	
	public int getSzanse(){return szanse;}
	
	public void odlacz(){
		
	}
	
	public static void main(String arg[]) throws UnknownHostException, IOException, ClassNotFoundException{
		int port = 9308;
		
		Gracz gracz = new Gracz(port);
		gracz.polacz();
		
		while(true){
			gracz.dzialaj();
		}
	}
}
