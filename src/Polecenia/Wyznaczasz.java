package Polecenia;

public class Wyznaczasz implements Polecenie {
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(Object opis){
		this.obiekt = (String)opis;
	}
}
