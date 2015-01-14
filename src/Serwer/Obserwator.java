package Serwer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Obserwator {
GraczKomunikacja tablicaKomunikcacji[];
private Obserwator obserwator;
private ServerSocket server;
private int port = 9999;


public Obserwator(){
	
	
	
}
public Obserwator(GraczKomunikacja tablicaKomunikcacji[]){
	this.tablicaKomunikcacji = tablicaKomunikcacji;
}
void aktualizacja() throws IOException{

	
}

public void zbierzGraczy() throws IOException{
    System.out.println("Waiting for client message...");
    
    //
    // Petla do akceptowania kolejnych klientow
    // 
    //
    for(int i=0; i<10; i++) {
    	
    	
            Socket socket = server.accept();
            new GraczKomunikacja(socket);
            System.out.println(i);
        
    }
    
}


}
