package Gracz.stan;

import Gracz.Gracz;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Odpowiada implements StanGracza {
	public void graj(Gracz gracz){		
		Scanner scanner = new Scanner(System.in);
		String odpowiedz = scanner.nextLine();
		scanner.close();
		
		try{
			new ObjectOutputStream(gracz.getGniazdo().getOutputStream()).writeObject(odpowiedz);
		}
		catch(Exception e){
			System.out.println("Blad przy wysylaniu odpowiedzi!");
		}
		
		gracz.ustalStan(new Czeka());
	}
}