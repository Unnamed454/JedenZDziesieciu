package Polecenia;

public class FabrykaOdpowiadania implements FabrykaPolecen {
	public Polecenie stworzPolecenie(){
		return new Odpowiadasz();
	}
}