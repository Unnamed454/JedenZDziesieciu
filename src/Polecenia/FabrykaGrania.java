package Polecenia;

public class FabrykaGrania implements FabrykaPolecen {
	public Polecenie stworzPolecenie(){
		return new Grasz();
	}
}
