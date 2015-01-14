package Serwer;

public class Rekord {
	private String pytanie;
	private String odpowiedz;
	Rekord(){
		
	}
	Rekord(String pytanie, String opdowiedz){
		this.pytanie =pytanie;
		this.odpowiedz = opdowiedz;
	}
	public String getPytanie(){
		return this.pytanie;
		
	}
	public String getOdpowiedz(){
		return this.odpowiedz;
		
	}
	
}
