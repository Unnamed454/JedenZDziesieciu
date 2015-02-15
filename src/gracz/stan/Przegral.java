package Gracz.stan;

import Gracz.Gracz;

public class Przegral implements StanGracza {

	@Override
	public void graj(Gracz gracz) {
		gracz.odlacz();
	}

}
