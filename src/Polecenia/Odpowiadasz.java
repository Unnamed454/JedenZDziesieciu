
package Polecenia;

import Serwer.Rekord;
import Serwer.BazaDanych;

public class Odpowiadasz implements Polecenie{
	BazaDanych bd = new BazaDanych("cwdb.txt");
	
	public Rekord zwrocPowiazanyObiekt(){
		return bd.losujRekord();
	}
}