package it.fi.gf.meucci;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public ServerSocket socketserver;      
    public static ArrayList<Biglietto> biglietti = new ArrayList<>();

    public Server(){
        Biglietto b1 = new Biglietto(1, "palco-1a");
        Biglietto b2 = new Biglietto(2, "parterre-13a");
        Biglietto b3 = new Biglietto(3, "palco-4b");
        Biglietto b4 = new Biglietto(4, "tribuna-10c");

        biglietti.add(b1);
        biglietti.add(b2);
        biglietti.add(b3);
        biglietti.add(b4);
    }

    public void avvia(){
        try {
            System.out.println("Server in esecuzione...");
            this.socketserver = new ServerSocket(9843); 
            while (true) {                              
                Socket socket = socketserver.accept(); 
                ServerThread thread = new ServerThread(socket);
                thread.start();                
            }

        } catch (Exception e) {
            System.out.println("Il server è stato chiuso");
        }        
    }
}
