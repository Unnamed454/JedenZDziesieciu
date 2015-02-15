package Gracz.stan;

import Gracz.Gracz;

import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Wyznacza implements StanGracza{
	int nastepny;
	public void graj(Gracz gracz){
		do{
			System.out.println("Podaj nastepnego odpowiadaj¹cego(1-10)");
			Scanner scanner = new Scanner(System.in);
			String odpowiedz = scanner.nextLine();
			scanner.close();
			
			nastepny = Integer.valueOf(odpowiedz);
		}while(nastepny > 0 && nastepny <= 10);
		
		try{
			new ObjectOutputStream(gracz.getGniazdo().getOutputStream()).writeObject(nastepny);
		}
		catch(Exception e){
			System.out.println("B³¹d przy przesy³aniu odpowiedzi!");
		}
		
		gracz.ustalStan(new Czeka());
	}
}
