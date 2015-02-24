package Polecenia;

public class Wyznaczasz implements Polecenie<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -60700319724270654L;
	String obiekt;

	 public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	

	 public void ustawObiekt(String powiazany) {
		this.obiekt = powiazany;
		
	}
}
