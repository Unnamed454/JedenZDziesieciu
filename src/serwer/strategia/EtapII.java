package Serwer.strategia;

import java.io.IOException;

import Polecenia.Fabryka;
import Polecenia.FabrykaPolecen;
import Polecenia.Polecenie;
import Serwer.BazaDanych;
import Serwer.Rekord;
import Serwer.Serwer;

public class EtapII implements Strategia{
	BazaDanych bd = new BazaDanych("cwdb.txt");
	Rekord aktualnePytanie = new Rekord();
	Fabryka fabryka = new Fabryka();
	FabrykaPolecen fabrykaPolecen;
	Polecenie aktualnePolecenie;
	int aktualny = 1, stary = -1;
	
	public void graj(Serwer serwer){
		for(int i = 1; i <= 10; i++){
			if(serwer.getGracz(aktualny).czyGra() == true){
				try{
					fabrykaPolecen = fabryka.wybierzFabryke("Odpowiadasz");
					aktualnePolecenie = fabrykaPolecen.stworzPolecenie();
					aktualnePytanie = bd.losujRekord();
					aktualnePolecenie.ustawObiekt(aktualnePytanie.getPytanie());
					
					serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
					if(aktualnePytanie.sprawdzOdpowiedz(serwer.getGracz(aktualny).getWiadomoscZGniazda()) == true){
						serwer.setWynik(aktualny, "dodac", 0);
						
						fabrykaPolecen = fabryka.wybierzFabryke("Wyznaczasz");
						aktualnePolecenie = fabrykaPolecen.stworzPolecenie();
						aktualnePolecenie.ustawObiekt("Wyznacz nastepnego odpowiadajacego");
						
						serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
						
						break;
					}
					else{
						serwer.setWynik(aktualny, "odjac", 0);
						if(serwer.getWynik(aktualny)[0] == 0){
							fabrykaPolecen = fabryka.wybierzFabryke("Odpadasz");
							aktualnePolecenie = fabrykaPolecen.stworzPolecenie();
							serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
							
							serwer.getGracz(aktualny).setJuzNiegra();
							//TODO pewnie jeszcze do wyjebania z obserwatorow
						}
							
						break;
					}
				}
				catch (Exception e){
					System.out.println("Odpowiadanie sprawdzanie odp itd");
				}
				serwer.powiadamiaj();
			}
			
			if(aktualny == 10) aktualny = 1;
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
					fabrykaPolecen = fabryka.wybierzFabryke("Odpowiadasz");
					aktualnePolecenie = fabrykaPolecen.stworzPolecenie();
					aktualnePytanie = bd.losujRekord();
					aktualnePolecenie.ustawObiekt(aktualnePytanie.getPytanie());
					
					serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
					if(aktualnePytanie.sprawdzOdpowiedz(serwer.getGracz(aktualny).getWiadomoscZGniazda()) == true){
						serwer.setWynik(aktualny, "dodac", 0);
						
						fabrykaPolecen = fabryka.wybierzFabryke("Wyznaczasz");
						aktualnePolecenie = fabrykaPolecen.stworzPolecenie();
						aktualnePolecenie.ustawObiekt("Wyznacz nastepnego odpowiadajacego");
						
						serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
					}
					else{
						serwer.setWynik(aktualny, "odjac", 0);
						
						if(serwer.getWynik(aktualny)[0] == 0){
							fabrykaPolecen = fabryka.wybierzFabryke("Odpadasz");
							aktualnePolecenie = fabrykaPolecen.stworzPolecenie();
							serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
							
							serwer.getGracz(aktualny).setJuzNiegra();
							//TODO pewnie jeszcze do wyjebania z obserwatorow
						}
						
						aktualny = stary;
					}
				}
				else{
					aktualny = stary;
					
					fabrykaPolecen = fabryka.wybierzFabryke("Wyznaczasz");
					aktualnePolecenie = fabrykaPolecen.stworzPolecenie();
					aktualnePolecenie.ustawObiekt("Gracz o wybranym ID ju¿ nie gra - wyznacz innego");
					
					serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
				}
				serwer.powiadamiaj();
			}
			catch(Exception e){
				System.out.println("cos zle przy wyznaczaniu");
			}
		}
		//koniec etapu 2
	}
}