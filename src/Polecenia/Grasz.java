package Polecenia;

public class Grasz implements Polecenie<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2996800449930877036L;
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(String nazwa){
		this.obiekt = "Grasz";
	}
}