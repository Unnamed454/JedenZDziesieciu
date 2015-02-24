package Polecenia;

public class Czekasz implements Polecenie<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1885403319908167451L;
	String obiekt;
	
	public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	
	public void ustawObiekt(String nazwa){
		this.obiekt = "Czekasz";
	}
}