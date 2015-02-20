package Gracz.stan;

import Gracz.Gracz;

import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Wyznacza implements StanGracza{
	int nastepny;
	public void graj(Gracz gracz){
		do{
			System.out.println("Podaj nastepnego odpowiadaj�cego(1-10) !nie mo�na wskaza� siebie!");
			
			Scanner scanner = new Scanner(System.in);
			String odpowiedz = scanner.nextLine();
			
			nastepny = Integer.valueOf(odpowiedz);
		}while(nastepny <= 0 && nastepny > 10 || nastepny == gracz.getID());
		
		try{
			new ObjectOutputStream(gracz.getGniazdo().getOutputStream()).writeObject(Integer.toString(nastepny));
		}
		catch(Exception e){
			System.out.println("B��d przy przesy�aniu odpowiedzi!");
		}
		
		gracz.ustalStan(new Czeka());
	}
}
