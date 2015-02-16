package Gracz.stan;

import java.io.ObjectInputStream;

import Gracz.Gracz;
import Polecenia.Polecenie;

public class Update implements StanGracza {
	public void graj(Gracz gracz){		
		try {
			Polecenie odSerwera = (Polecenie) new ObjectInputStream(gracz.getGniazdo().getInputStream()).readObject();
			gracz.update((int[][]) odSerwera.zwrocPowiazanyObiekt());
		}
		catch (Exception e) {
			System.out.println("Blad przy odbieraniu tablicy wynikow!");
		}
		gracz.ustalStan(new Czeka());
	}
}