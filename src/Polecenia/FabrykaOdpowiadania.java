package Polecenia;

import Serwer.BazaDanych;

public class FabrykaOdpowiadania implements FabrykaPolecen {

	BazaDanych bd = new BazaDanych("cwdb.txt");
	public Polecenie zrobPolecenie() {
		Odpowiadasz odp = new Odpowiadasz();
		odp.ustawRekord(bd.losujRekord());
		return odp;
	
	}

}