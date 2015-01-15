package gra.serwer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class GraczKomunikacja implements Runnable {
    private Socket socket;
    private int gotSomeData;
    private Integer szanse = 0;
    private Integer punkty = 0;
 
    public GraczKomunikacja(Socket socket) throws IOException {
        this.socket = socket;
 
        Thread t = new Thread(this);
        t.start();
    }
    public void wyslij(String msg) throws IOException{
    	new ObjectOutputStream(socket.getOutputStream()).writeObject(msg);
    }
 
    public void run(){
        try{
        	wait();
        	wyslij("Y:"+szanse.toString()+":"+punkty.toString());
        	
        	while(true){
        		gotSomeData = socket.getInputStream().available();
        		if(gotSomeData > 0) {
        			String odKlienta = (String) new ObjectInputStream(socket.getInputStream()).readObject();
        		}
        		else continue;
        	}
        }catch (IOException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
