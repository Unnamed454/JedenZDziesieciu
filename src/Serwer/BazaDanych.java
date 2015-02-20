package Serwer;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class BazaDanych {
	public Vector<Rekord> pytania = new Vector<Rekord>();
	
	public BazaDanych(String nazwaPliku){
		try{
			@SuppressWarnings("resource")
			Scanner in = new Scanner(new FileReader(nazwaPliku));
			
			while(in.hasNext()){
				String odp = in.nextLine();
				String pyt = in.nextLine();				
				pytania.add(new Rekord(pyt,odp));
			}
		}
		catch(Exception e){
			System.out.println("Cos w bazie danych prawdopodobnie brak pliku");
		}
	}
	
	public Rekord losujRekord(){
		Random rand = new Random();
		return this.pytania.get(rand.nextInt(pytania.size()));
	}
}
