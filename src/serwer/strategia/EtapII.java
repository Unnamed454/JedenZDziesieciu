package Serwer.strategia;

import Polecenia.Odpowiadasz;
import Serwer.Serwer;

public class EtapII implements Strategia{
	int aktualny = 1;
	int ilePozostalo;
	
	public void graj(Serwer serwer){
		while(true){
			if(serwer.getGracz(aktualny).czyGra() == true){
				try{
					serwer.getGracz(aktualny).wyslij(new Odpowiadasz());
					//formowanie pytania t¹ fabryk¹ + sprawdzanie odpowiedzi + uaktualnianie tablicy wynikow
					//if dobra odpowiedz to cos takiego: setWynik(aktualny, "dodac", 0); oraz serwer.getGracz(aktualny).wyslij(new Wyznaczasz()); i break;
					//if zla dopowiedz to cos takiego: setWynik(aktualny, "odjac", 0); oraz serwer.getGracz(aktualny).wyslij(new Czekasz());
					break; //to nie, ale czerwieni mi kod jak niema na tym etapie :P	
				}
				catch (Exception e){
					System.out.println("Odpowiadanie sprawdzanie odp itd");
				}
				serwer.powiadamiaj();
			}
			if(aktualny == 10) aktualny = 1;
			else{
				aktualny += 1;
			}
			ilePozostalo = serwer.ileGra();
			if(ilePozostalo == 3)
				break;
		}
		
		while(ilePozostalo > 3){
			aktualny = Integer.valueOf(serwer.getGracz(aktualny).getWiadomoscZGniazda());
			if(serwer.getGracz(aktualny).czyGra() == true){
				//to samo sprawdzanie odpowiedzi sratatata itd itp
			}
		
			ilePozostalo = serwer.ileGra();
		}
		//koniec etapu 2
	}
}