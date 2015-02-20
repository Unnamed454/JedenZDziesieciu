package Polecenia;

import java.util.Vector;

public class Aktualizacja implements Polecenie {
	Vector<Integer[]> obiekt;
	
	public Vector<Integer[]> zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(Object tablica){
		this.obiekt = (Vector<Integer[]>)tablica;
	}
}