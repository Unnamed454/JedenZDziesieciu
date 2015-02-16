package Polecenia;

public class Fabryka {
	public FabrykaPolecen wybierzFabryke(String nazwaPolecenia){
		FabrykaPolecen zwracana = null;
		
		switch(nazwaPolecenia){
			case "Czekasz":
				zwracana = new FabrykaCzekania();
				break;
			case "Odpowiadasz":
				zwracana = new FabrykaOdpowiadania();
				break;
			case "Odpadasz":
				zwracana = new FabrykaOdpadania();
				break;
			case "Grasz":
				zwracana = new FabrykaGrania();
				break;
			case "Wyznaczasz":
				zwracana = new FabrykaWyznaczania();
				break;
		}
		return zwracana;
	}
}
