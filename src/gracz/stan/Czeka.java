package gracz.stan;

import gracz.Gracz;
import java.io.ObjectInputStream;

public class Czeka implements StanGracza{
	@Override
	public void graj(Gracz gracz){
		try{
			String odSerwera = (String) new ObjectInputStream(gracz.getGniazdo().getInputStream()).readObject();
			
			if(odSerwera.equals("Y")) gracz.ustalStan(new Odpowiada());
			if(odSerwera.equals("Up")) gracz.ustalStan(new Update());
			//else if(odSerwera.equals("++")) gracz.etap++;
			//else if(odSerwera.equals("N")) return;
		}
		catch(Exception e){
			System.out.println("B³¹d przy odczytywaniu!");
		}
	}
}
