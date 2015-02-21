package Polecenia;

public class Grasz implements Polecenie<String> {
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(String nazwa){
		this.obiekt = "Grasz";
	}
}