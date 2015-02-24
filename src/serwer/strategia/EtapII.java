package Serwer.strategia;

import java.io.PrintStream;

import Polecenia.Odpadasz;
import Polecenia.Odpowiadasz;
import Polecenia.Polecenie;
import Polecenia.Wyznaczasz;
import Serwer.BazaDanych;
import Serwer.Rekord;
import Serwer.Serwer;

public class EtapII implements Strategia{
	BazaDanych bd = new BazaDanych("cwdb.txt");
	Rekord aktualnePytanie = new Rekord();
	Polecenie<String> aktualnePolecenie;
	int aktualny = 1, stary = -1;
	
	public void graj(Serwer serwer){
		for(int i = 1; i <= 10; i++){
			if(serwer.getGracz(aktualny).czyGra() == true){
				try{
					aktualnePolecenie = new Odpowiadasz();
					aktualnePytanie = bd.losujRekord();
					aktualnePolecenie.ustawObiekt(aktualnePytanie.getPytanie());
					
					serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
					if(aktualnePytanie.sprawdzOdpowiedz(serwer.getGracz(aktualny).getWiadomoscZGniazda()) == true){
						serwer.setWynik(aktualny, "dodac", 0);
						
						aktualnePolecenie = new Wyznaczasz();
						aktualnePolecenie.ustawObiekt("Wyznacz nastepnego odpowiadajacego");
						
						serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
						
						break;
					}
					else{
						serwer.setWynik(aktualny, "odjac", 0);
						if(serwer.getWynik(aktualny)[0] == 0){
							aktualnePolecenie = new Odpadasz();
							serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
							
							serwer.getGracz(aktualny).setJuzNiegra();
						}
					}
				}
				catch (Exception e){
					System.out.println("Odpowiadanie sprawdzanie odp itd");
				}
			serwer.powiadamiaj();
			}
			
			if(aktualny == 10) {aktualny = 1; i = 0;}
			else
				aktualny++;
			
			if(serwer.ileGra() <= 3)
				break;
		}
				
		while(serwer.ileGra() > 3){
			try{
				stary = aktualny;
				aktualny = Integer.valueOf(serwer.getGracz(aktualny).getWiadomoscZGniazda());
				if(serwer.getGracz(aktualny).czyGra() == true){
					aktualnePolecenie = new Odpowiadasz();
					aktualnePytanie = bd.losujRekord();
					aktualnePolecenie.ustawObiekt(aktualnePytanie.getPytanie());
					
					serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
					if(aktualnePytanie.sprawdzOdpowiedz(serwer.getGracz(aktualny).getWiadomoscZGniazda()) == true){
						serwer.setWynik(aktualny, "dodac", 0);
						
						aktualnePolecenie = new Wyznaczasz();
						aktualnePolecenie.ustawObiekt("Wyznacz nastepnego odpowiadajacego");
						
						serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
					}
					else{
						serwer.setWynik(aktualny, "odjac", 0);
						
						if(serwer.getWynik(aktualny)[0] == 0){
							aktualnePolecenie = new Odpadasz();
							serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
							
							//TODO jeszcze wywalic z obserwatorow
						}
						
						aktualny = stary;
						
						aktualnePolecenie = new Wyznaczasz();
						aktualnePolecenie.ustawObiekt("Wybrany gracz nie odpowiedzial poprawnie - wyznaczasz ponownie!");
						
						serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
					}
				}
				else{
					aktualny = stary;
					
					aktualnePolecenie = new Wyznaczasz();
					aktualnePolecenie.ustawObiekt("Gracz o wybranym ID ju¿ nie gra - wyznacz innego");
					
					serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
				}
				serwer.powiadamiaj();
			}
			catch(Exception e){
				e.printStackTrace(new PrintStream(System.out));
			}
		}
		//koniec etapu 2
	}
}