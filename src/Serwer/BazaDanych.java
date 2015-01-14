package Serwer;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	public static void main(String[] args) throws FileNotFoundException {
		BazaDanych db = new BazaDanych("cwdb.txt");
		System.out.println(db.pytania.size());
		for(int i=0; i<db.pytania.size();i++){
			System.out.println(db.pytania.get(i).getPytanie()+ "    " +db.pytania.get(i).getOdpowiedz());
		}
	}
}
