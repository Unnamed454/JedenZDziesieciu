package gra.serwer;

import java.io.IOException;
import java.net.Socket;

public interface Obserwowany {
	void dodajObserwatora(Socket socket);
	void usunObserwatora(int pozycjaGracza);
	void informujObserwatorow() throws IOException;
}
