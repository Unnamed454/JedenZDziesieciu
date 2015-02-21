package Polecenia;

import java.io.Serializable;

public interface Polecenie<V> extends Serializable{
	  public V zwrocPowiazanyObiekt();
	 public void ustawObiekt(V powiazany);
	
}
