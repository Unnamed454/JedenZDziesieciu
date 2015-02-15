package Serwer.strategia;

import java.io.IOException;

import Serwer.GraczKomunikacja;
import Serwer.Serwer;
import Polecenia.*;

public class EtapI implements Strategia {
	int aktualny = 1;

	public void graj(Serwer serwer){
		for(int i = 0; i < 2; i++){
			do{
				try{
					serwer.getGracz(aktualny).wyslij(new Odpowiadasz());
					//formowanie pytania t¹ fabryk¹ + sprawdzanie odpowiedzi + uaktualnianie tablicy wynikow
					//if dobra odpowiedz to cos takiego: setWynik(aktualny, "dodac", 0);
					//if zla dopowiedz to cos takiego: setWynik(aktualny, "odjac", 0);
				}
				catch (Exception e) {
					System.out.println("Odpowiadanie sprawdzanie odp itd");
				}
				serwer.powiadamiaj();
				aktualny += 1;
			}while(aktualny <= 10);
		
			aktualny = 1;
		}
		
		for(int i = 1; i <= 10; i++){
			if(serwer.getWynik(i)[0] == 1)
				try{
					serwer.getGracz(i).wyslij(new Odpadasz());
				}
				catch(IOException e){
					System.out.println("Wyrzuc gracza! - jakis problem");
				}
		}
		//koniec etapu 1 zaczyna sie etap 2
	}
}