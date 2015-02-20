package Serwer.obserwator;

import Serwer.GraczKomunikacja;

public interface Obserwowany {
	public void powiadamiaj();
	public void dodajObserwatora(GraczKomunikacja komunikacja);
	public void usunObserwatora(int id);
}