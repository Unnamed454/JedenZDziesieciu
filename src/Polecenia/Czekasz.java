package Polecenia;

public class Czekasz implements Polecenie<String> {
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(String nazwa){
		this.obiekt = "Czekasz";
	}
}