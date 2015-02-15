package Gracz.stan;

import Gracz.Gracz;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Odpowiada implements StanGracza {

	@Override
	public void graj(Gracz gracz){
		Scanner scanner = new Scanner(System.in);
		String odpowiedz = scanner.nextLine();
		scanner.close();
		
		try{
			new ObjectOutputStream(gracz.getGniazdo().getOutputStream()).writeObject(odpowiedz);
			
			String odSerwera = (String) new ObjectInputStream(gracz.getGniazdo().getInputStream()).readObject();

			if(odSerwera.equals("Y")) {
				if(gracz.getEtap() == 1) gracz.ustalStan(new Czeka());
				else if(gracz.getEtap() == 2) gracz.ustalStan(new Wyznacza());
				else if(gracz.getEtap() == 3) {
					gracz.ustalStan(new Update());
				}
			}
			else if(odSerwera.equals("N")) {
				if(gracz.getSzanse() != 0) {
					gracz.ustalStan(new Czeka());
				}
				else gracz.ustalStan(new Przegral());
			}
		}
		catch(Exception e){
			System.out.println("B³¹d przy wysy³aniu odpowiedzi!");
		}
	}
}