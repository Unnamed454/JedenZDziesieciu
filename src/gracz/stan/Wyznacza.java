package Gracz.stan;

import Gracz.Gracz;

import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Wyznacza implements StanGracza{
	int nastepny;
	public void graj(Gracz gracz){
		do{
			System.out.println("Podaj nastepnego odpowiadającego(1-10)");
			
			Scanner scanner = new Scanner(System.in);
			String odpowiedz = scanner.nextLine();
			scanner.close();
			
			nastepny = Integer.valueOf(odpowiedz);
		}while(nastepny > 0 && nastepny <= 10);
		
		try{
			new ObjectOutputStream(gracz.getGniazdo().getOutputStream()).writeObject(nastepny);
		}
		catch(Exception e){
			System.out.println("Błąd przy przesyłaniu odpowiedzi!");
		}
		
		gracz.ustalStan(new Czeka());
	}
}
