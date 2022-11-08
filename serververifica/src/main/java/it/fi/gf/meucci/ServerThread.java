package it.fi.gf.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerThread extends Thread{
    Socket client = null;

    BufferedReader inDalClient = null;
    DataOutputStream outVersoIlClient = null;

    ServerThread(Socket c ){
        this.client = c;
    }

    public void run(){
        try {
            this.comunica();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void comunica() throws Exception{
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoIlClient = new DataOutputStream(client.getOutputStream());
        
        ObjectMapper objectMapper = new ObjectMapper();

        while(true){
            String stringa_ricevuta = inDalClient.readLine();
            Messaggio messaggio = objectMapper.readValue(stringa_ricevuta, Messaggio.class); //deserializzo il messaggio che mi arriva dal client

            if (messaggio.getLista_biglietti().size() == 0){
                Messaggio m = new Messaggio(Server.biglietti);
                outVersoIlClient.writeBytes(objectMapper.writeValueAsString(m) + "\n"); //serializzo quello da mandare al client
            } else {

                ArrayList<Biglietto> biglietti_acquistati = new ArrayList<>();

                for (int i = 0; i < messaggio.getLista_biglietti().size(); i++) {
                    for (int j = 0; j < Server.biglietti.size(); j++) {
                        if (messaggio.getLista_biglietti().get(i).ID == Server.biglietti.get(j).ID){
                            biglietti_acquistati.add(messaggio.getLista_biglietti().get(i));
                            Server.biglietti.remove(j);
                            j--;
                        }
                    }
                }

                Messaggio m = new Messaggio(biglietti_acquistati);
                outVersoIlClient.writeBytes(objectMapper.writeValueAsString(m) + "\n");
            }
        }
    }  
}
