
package Polecenia;

public class Odpowiadasz implements Polecenie{
	String pytanie;
	
	public String zwrocPowiazanyObiekt(){
		return pytanie;
	}
	
	public void ustawObiekt(Object pytanie){
		this.pytanie = (String)pytanie;
	}
}