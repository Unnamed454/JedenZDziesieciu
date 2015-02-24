package Polecenia;

import java.util.Vector;

public class Aktualizacja implements Polecenie<Vector<Integer[]>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6644412115969530181L;
	Vector<Integer[]> obiekt;
	
	public Vector<Integer[]> zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(Vector<Integer[]> tablica){
		this.obiekt = tablica;
	}
}