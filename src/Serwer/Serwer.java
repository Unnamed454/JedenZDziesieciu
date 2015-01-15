package Serwer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Serwer {
	private Obserwator obserwator;
	private ServerSocket server;
    private int port = 930;
    private Socket socket;
    private int current = 1;
    
    public Serwer() throws IOException{
            server = new ServerSocket(port);
            server.setReuseAddress(true);
    }
 
    public static void main(String[] args) throws IOException, InterruptedException {
        Serwer example = new Serwer();
        example.zbierzGraczy();
    }
    
    public void zbierzGraczy() throws IOException, InterruptedException{
	    System.out.println("Waiting for client message...");

	    GraczKomunikacja tablicaGraczy[] = new GraczKomunikacja[10];
	    for(int i=0; i<10; i++) {
	            socket = server.accept();
	            tablicaGraczy[i] = new GraczKomunikacja(socket);
	            System.out.println(i);
	    }
	    Thread.sleep(1000);
	    tablicaGraczy.notifyAll();
	    
	    obserwator = new Obserwator(tablicaGraczy);
    }
}
 


