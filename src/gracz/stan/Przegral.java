package gracz.stan;

import gracz.Gracz;

public class Przegral implements StanGracza {

	@Override
	public void graj(Gracz gracz) {
		gracz.odlacz();
	}

}