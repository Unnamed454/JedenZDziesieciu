package Serwer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class GraczKomunikacja implements Runnable {
    private Socket socket;
    private ObjectInputStream ois;
    private  ObjectOutputStream oos;
    private String stringDoWyslania;
    private String otrzymanyOdKlienta;
 
    public GraczKomunikacja(Socket socket) throws IOException {
        this.socket = socket;
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
 
        Thread t = new Thread(this);
        t.start();
    }
    public void wyslij(String msg) throws IOException{
    	oos.writeObject(msg);
    }
 
    public void run() {
        try
        {
            //
            // Odczytaj klienta
            //
            
        	while(true){
           this.otrzymanyOdKlienta = (String) ois.readObject();
            System.out.println("Message Received: " + this.otrzymanyOdKlienta);
 
            //
            // wyslij do klienta
      
            this.wyslij(stringDoWyslania);
 
        	}
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
