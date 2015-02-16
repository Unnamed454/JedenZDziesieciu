package Serwer;

public class Rekord{
	private String pytanie;
	private String odpowiedz;
	
	public Rekord(){
		
	}
	
	Rekord(String pytanie, String opdowiedz){
		this.pytanie = pytanie;
		this.odpowiedz = opdowiedz;
	}

	public String getPytanie(){
		return this.pytanie;
	}
	
	public String getOdpowiedz(){
		return this.odpowiedz;
	}
	
	public boolean sprawdzOdpowiedz(String odpowiedz){
		if (odpowiedz.equalsIgnoreCase(this.odpowiedz))
			return true;
		else
			return false;
	}
}