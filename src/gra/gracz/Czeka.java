package gra.gracz;

public class Czeka implements StanGracza {

	@Override
	public void raport(Gracz gracz) {
		System.out.println("Czeka na pytanie");
	}
}
