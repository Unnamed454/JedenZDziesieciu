package Polecenia;

public class Grasz implements Polecenie {
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(Object nazwa){
		this.obiekt = "Grasz";
	}
}