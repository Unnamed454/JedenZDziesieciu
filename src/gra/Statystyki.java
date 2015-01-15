package gra;

public class Statystyki {
	private int punkty;
	private int szanse;
	
	public Statystyki(){
		this.punkty = 0;
		this.szanse = 3;
	}
	
	public void dodajPunkty(int ileDodac){
		this.punkty += ileDodac;
	}
	
	public void odejmijSzanse(){
		this.szanse--;
	}
	
	public int wezPkt(){
		return this.punkty;
	}
	
	public int wezSzanse(){
		return this.szanse;
	}
}
