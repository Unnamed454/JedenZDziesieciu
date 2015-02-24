package Serwer.strategia;

import java.io.IOException;

import Serwer.BazaDanych;
import Serwer.Rekord;
import Serwer.Serwer;
import Polecenia.*;

public class EtapI implements Strategia{
	BazaDanych bd = new BazaDanych("cwdb.txt");
	Rekord aktualnePytanie = new Rekord();
	Polecenie<String> aktualnePolecenie;
	int aktualny = 1;
	

	public void graj(Serwer serwer){		
		for(int i = 0; i < 2; i++){
			do{
				try{
					aktualnePolecenie = new Odpowiadasz();
					aktualnePytanie = bd.losujRekord();
					aktualnePolecenie.ustawObiekt(aktualnePytanie.getPytanie());
					
					serwer.getGracz(aktualny).wyslij(aktualnePolecenie);
					if(aktualnePytanie.sprawdzOdpowiedz(serwer.getGracz(aktualny).getWiadomoscZGniazda()) == true)
						serwer.setWynik(aktualny, "dodac", 0);
					else
						serwer.setWynik(aktualny, "odjac", 0);
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
					aktualnePolecenie = new Odpadasz();
					serwer.getGracz(i).wyslij(aktualnePolecenie);
					
					serwer.setWynik(i, "odjac", 0);
				}
				catch(IOException e){
					System.out.println("Wyrzuc gracza! - jakis problem");
				}
		}
		serwer.powiadamiaj();
	}
}