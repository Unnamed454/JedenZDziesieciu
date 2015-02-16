package Serwer.strategia;

import java.io.IOException;

import Serwer.BazaDanych;
import Serwer.Rekord;
import Serwer.Serwer;
import Polecenia.*;

public class EtapI implements Strategia{
	BazaDanych bd = new BazaDanych("cwdb.txt");
	Rekord aktualnePytanie = new Rekord();
	Fabryka fabryka = new Fabryka();
	FabrykaPolecen fabrykaPolecen;
	Polecenie aktualnePolecenie;
	int aktualny = 1;

	public void graj(Serwer serwer){
		for(int i = 0; i < 2; i++){
			do{
				try{
					fabrykaPolecen = fabryka.wybierzFabryke("Odpowiadasz");
					aktualnePolecenie = fabrykaPolecen.stworzPolecenie();
					aktualnePytanie = bd.losujRekord();
					aktualnePolecenie.ustawObiekt(aktualnePytanie.getPytanie());
					
					serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
					if(aktualnePytanie.sprawdzOdpowiedz(serwer.getGracz(aktualny).getWiadomoscZGniazda()) == true)
						serwer.setWynik(aktualny, "dodac", 0);
					else
						serwer.setWynik(aktualny, "odjac", 0);
					
					fabrykaPolecen = fabryka.wybierzFabryke("Czekasz");
					aktualnePolecenie = fabrykaPolecen.stworzPolecenie();
					serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
				}
				catch (Exception e){
					System.out.println("Odpowiadanie sprawdzanie odp itd");
				}
				
				serwer.powiadamiaj();
			}while(++aktualny <= 10);
			aktualny = 1;
		}
		
		for(int i = 1; i <= 10; i++){
			if(serwer.getWynik(i)[0] == 1)
				try{
					fabrykaPolecen = fabryka.wybierzFabryke("Odpadasz");
					aktualnePolecenie = fabrykaPolecen.stworzPolecenie();
					serwer.getGracz(i).wyslij(aktualnePolecenie);
				}
				catch(IOException e){
					System.out.println("Wyrzuc gracza! - jakis problem");
				}
		}
	}
}