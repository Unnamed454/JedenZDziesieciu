package Gracz.stan;

import Gracz.Gracz;
import Polecenia.*;
import java.io.ObjectInputStream;

public class Czeka implements StanGracza{
	public void graj(Gracz gracz){
		try{
			Polecenie odSerwera = (Polecenie) new ObjectInputStream(gracz.getGniazdo().getInputStream()).readObject();
			
			if(odSerwera instanceof Czekasz) gracz.ustalStan(new Czeka());
			if(odSerwera instanceof Odpowiadasz){
				System.out.println("Odpowiadasz na pytanie: " + (String)odSerwera.zwrocPowiazanyObiekt());
				gracz.ustalStan(new Odpowiada());
			}
			if(odSerwera instanceof Odpadasz) gracz.ustalStan(new Przegral());
			if(odSerwera instanceof Wyznaczasz) gracz.ustalStan(new Wyznacza());
			if(odSerwera instanceof Aktualizacja){
				gracz.update((int[][]) odSerwera.zwrocPowiazanyObiekt());
				gracz.ustalStan(new Czeka());
			}
		}
		catch(Exception e){
			System.out.println("B³¹d przy odczytywaniu!");
		}
	}
}