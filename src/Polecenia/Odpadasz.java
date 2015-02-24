package Polecenia;

public class Odpadasz implements Polecenie<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4995910806638464053L;
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(String nazwa){
		this.obiekt = "Odpadasz";
	}
}