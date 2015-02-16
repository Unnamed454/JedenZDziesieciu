package Polecenia;

import java.io.Serializable;

public interface Polecenie extends Serializable{
	public Object zwrocPowiazanyObiekt();
	public void ustawObiekt(Object powiazany);
}
