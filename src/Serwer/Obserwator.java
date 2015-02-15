package Serwer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Obserwator {
	GraczKomunikacja tablicaKomunikcacji[];
	private Obserwator obserwator;
	private ServerSocket server;
	
	public Obserwator(GraczKomunikacja tablicaKomunikcacji[]){
		this.tablicaKomunikcacji = tablicaKomunikcacji;
	}
	
	void aktualizacja() throws IOException{
	
		
	}
}
