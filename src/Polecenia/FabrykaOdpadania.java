package Polecenia;

public class FabrykaOdpadania implements FabrykaPolecen {
	public Polecenie stworzPolecenie(){
		return new Odpadasz();
	}
}
