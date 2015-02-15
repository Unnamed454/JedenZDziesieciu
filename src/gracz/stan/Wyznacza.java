package Gracz.stan;

import Gracz.Gracz;

import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Wyznacza implements StanGracza{
	@Override
	public void graj(Gracz gracz){ //ta odpowiedz musi byæ parsowana do inta - to bedzie nr od 1 do 10 ktory nastepny czlowieczek odpowiada
		Scanner scanner = new Scanner(System.in);
		String odpowiedz = scanner.nextLine();
		scanner.close();
		try{
			new ObjectOutputStream(gracz.getGniazdo().getOutputStream()).writeObject(odpowiedz);
		}
		catch(Exception e){
			System.out.println("B³¹d przy przesy³aniu odpowiedzi!");
		}
		gracz.ustalStan(new Czeka());
	}
}
