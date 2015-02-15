package Gracz.stan;

import java.io.ObjectInputStream;
import Gracz.Gracz;

public class Update implements StanGracza {
	public void graj(Gracz gracz){		
		try {
			int[][] tablicaWynikow_Aktualna = (int[][]) new ObjectInputStream(gracz.getGniazdo().getInputStream()).readObject();
			gracz.update(tablicaWynikow_Aktualna);
		}
		catch (Exception e) {
			System.out.println("Blad przy odbieraniu tablicy wynikow!");
		}
		gracz.ustalStan(new Czeka());
	}
}