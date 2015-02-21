package Polecenia;

public class Wyznaczasz implements Polecenie<String> {
	String obiekt;

	 public String zwrocPowiazanyObiekt(){
		return obiekt;
	}
	

	 public void ustawObiekt(String powiazany) {
		this.obiekt = powiazany;
		
	}
}
