package Polecenia;

public class Odpadasz implements Polecenie<String> {
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(String nazwa){
		this.obiekt = "Odpadasz";
	}
}