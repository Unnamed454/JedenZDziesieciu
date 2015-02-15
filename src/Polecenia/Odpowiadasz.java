
package Polecenia;

import Serwer.Rekord;

public class Odpowiadasz implements Polecenie {
Rekord rekord;

public Object zwrocPowiazanyObiekt() {
	// TODO Auto-generated method stub
	return this.rekord;
}

public void ustawRekord(Rekord rekord){
	this.rekord = rekord;
}


}
