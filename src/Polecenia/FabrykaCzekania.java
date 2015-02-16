package Polecenia;

public class FabrykaCzekania implements FabrykaPolecen {
	public Polecenie stworzPolecenie() {
		return new Czekasz();
	}
}
