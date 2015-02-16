package Polecenia;

public class Aktualizacja implements Polecenie {
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(Object nazwa){
		this.obiekt = "Aktualizacja";
	}
}