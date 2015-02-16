package Polecenia;

public class FabrykaAktualizacji implements FabrykaPolecen {
	public Polecenie stworzPolecenie() {
		return new Aktualizacja();
	}
}
