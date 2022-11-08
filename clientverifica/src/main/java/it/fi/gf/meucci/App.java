package it.fi.gf.meucci;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        Client client = new Client();
        client.connetti();
        client.comunica();
    }
}
