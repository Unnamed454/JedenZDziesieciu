package Serwer;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class BazaDanych {
	public Vector<Rekord> pytania = new Vector<Rekord>();

	BazaDanych(){
		
	}
	public BazaDanych(String nazwaPliku){
		try{
			Scanner in = new Scanner(new FileReader(nazwaPliku));
			String pytanie, odpowiedz;
			while(in.hasNext()){
				System.out.println("a");
				
				String pyt = in.nextLine();
				System.out.println(pyt);
				String odp = in.nextLine();
				System.out.println(odp);
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
