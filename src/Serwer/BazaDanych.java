package serwer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class BazaDanych {
	public Vector<Rekord> pytania = new Vector<Rekord>();

	BazaDanych(){
		
	}
	BazaDanych(String nazwaPliku) throws FileNotFoundException{
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
	public Rekord losujRekord(){
		Random rand = new Random();
		return this.pytania.get(rand.nextInt(pytania.size()));
	
	}

}
