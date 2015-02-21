
package Polecenia;

public class Odpowiadasz implements Polecenie<String>{
	String pytanie;
	
	public String zwrocPowiazanyObiekt(){
		return pytanie;
	}
	
	public void ustawObiekt(String pytanie){
		this.pytanie = pytanie;
	}
}