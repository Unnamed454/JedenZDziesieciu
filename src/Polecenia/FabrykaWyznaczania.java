package Polecenia;

public class FabrykaWyznaczania implements FabrykaPolecen{
	public Polecenie stworzPolecenie() {
		return new Wyznaczasz();
	}
}