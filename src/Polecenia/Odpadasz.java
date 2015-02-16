package Polecenia;

public class Odpadasz implements Polecenie {
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(Object nazwa){
		this.obiekt = "Odpadasz";
	}
}