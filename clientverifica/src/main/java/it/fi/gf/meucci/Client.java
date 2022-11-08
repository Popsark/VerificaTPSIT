package it.fi.gf.meucci;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {
    
    String nomeServer = "localhost"; 
    int porta = 9843;
    Socket socket;
    String stringaRicevuta;
    String stringainviata;
    BufferedReader tastiera;
    BufferedReader inDalServer;
    DataOutputStream outVersoIlServer;

    public void comunica(){
        try {
            
                ArrayList<Biglietto> bigliettiDaAcq = new ArrayList<>();
                System.out.print("Scrivi gli ID dei biglietti che desideri acquistare");
                stringaRicevuta = tastiera.readLine();
                String[] arrayStr = stringaRicevuta.split(" "); //Utilizzo un metodo che mi permette di poter scrivere gli ID dei biglietti 
                                                            //che voglio acquistare su una sola riga di comando facendoli distanziare da uno spazio

                for (int i = 0 ; i<arrayStr.length; i++){
                    Biglietto b = new Biglietto( Integer.parseInt( arrayStr[i] )," ");  //converto la stringa di id che voglio acquistare in int, 
                                                                                                //in modo da aggiungere il biglietto alla lista di biglietti da comprare
                    bigliettiDaAcq.add(b);
                }

                ObjectMapper objectMapper = new ObjectMapper();

                Messaggio m = new Messaggio(bigliettiDaAcq);
                outVersoIlServer.writeBytes(objectMapper.writeValueAsString(m) + "\n"); //serializzo il messaggio da inviare al server

                String risposta = inDalServer.readLine();
                Messaggio messaggioricevuto = objectMapper.readValue(risposta, Messaggio.class); //deserializzo il messaggio arrivato dal server

                System.out.println("I biglietti acquistati sono:");
                for (int i = 0; i < messaggioricevuto.lista_biglietti.size(); i++) {
                    System.out.println("ID biglietto : " + messaggioricevuto.lista_biglietti.get(i).ID);
                }
                System.out.println("L'acquisto è andato a buon fine, adesso verrai disconnesso");

                
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Socket connetti(){
        
        try {
            
            ObjectMapper objectMapper = new ObjectMapper();

            tastiera = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(nomeServer, porta);

            outVersoIlServer = new DataOutputStream(socket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader( socket.getInputStream() ));


            ArrayList<Biglietto> listVuota = new ArrayList<>();
            Messaggio m = new Messaggio(listVuota);

            outVersoIlServer.writeBytes(objectMapper.writeValueAsString(m) + "\n");

            String bigliettiDisponibili = inDalServer.readLine();
            Messaggio messaggio = objectMapper.readValue(bigliettiDisponibili, Messaggio.class);

            System.out.println("Verranno mostrati i biglietti disponibili: ");
            for (int i = 0; i < messaggio.lista_biglietti.size(); i++) {
                System.out.println("=>ID biglietto: " + messaggio.lista_biglietti.get(i).ID + " : " + messaggio.lista_biglietti.get(i).numero_biglietto +"\n" +"*/*/*/*/*/*/*/*/*/*/*");
            }

        } catch (Exception e) {
            System.out.println("c'è stato un errore");
        }

        return socket;
    }


    


}
