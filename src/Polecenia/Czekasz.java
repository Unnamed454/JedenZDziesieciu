package Polecenia;

public class Czekasz implements Polecenie {
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(Object nazwa){
		this.obiekt = "Czekasz";
	}
}