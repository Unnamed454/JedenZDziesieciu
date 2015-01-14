package Serwer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Serwer {
	private Obserwator obserwator;
	private ServerSocket server;
    private int port = 9996;
 
    public Serwer() throws IOException {
        
            server = new ServerSocket(port);
            obserwator = new Obserwator();
        
    }
 
    public static void main(String[] args) throws IOException {
        Serwer example = new Serwer();
        example.zbierzGraczy();
       
      
    }
    public void zbierzGraczy() throws IOException{
        System.out.println("Waiting for client message...");
        
        //
        // Petla do akceptowania kolejnych klientow
        // 
        //
        GraczKomunikacja tablicaGraczy[] = new GraczKomunikacja[10];
        for(int i=0; i<10; i++) {
        	
        	
                Socket socket = server.accept();
                tablicaGraczy[i] = new GraczKomunikacja(socket);
                System.out.println(i);
            
        }
     obserwator = new Obserwator(tablicaGraczy);   
    
}
}
 


