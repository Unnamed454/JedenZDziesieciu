package Polecenia;

public class Aktualizacja implements Polecenie {
	int[][] obiekt;
	
	public int[][] zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(Object tablica){
		this.obiekt = (int[][])tablica;
	}
}