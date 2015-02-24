
package Polecenia;

public class Odpowiadasz implements Polecenie<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2145221700391942836L;
	String pytanie;
	
	public String zwrocPowiazanyObiekt(){
		return pytanie;
	}
	
	public void ustawObiekt(String pytanie){
		this.pytanie = pytanie;
	}
}