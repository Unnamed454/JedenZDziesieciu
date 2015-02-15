package Serwer.strategia;

import Serwer.Serwer;

public class EtapIII implements Strategia {
	int[] idpozostalych = new int[3];
	int liczbaZadanychPytan = 0;
	
	//przedefiniowanie wszystkich pozostalych na id 1,2,3
	public void graj(Serwer serwer) {
		int index = 0;
		
		for(int i = 1; i <= 10; i++){
			if(serwer.getGracz(i).czyGra() == true){
				idpozostalych[index] = i;
				index++;
			}
		}
	}
}