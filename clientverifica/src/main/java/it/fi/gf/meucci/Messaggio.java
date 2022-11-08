package it.fi.gf.meucci;


import java.util.ArrayList;

public class Messaggio {
        ArrayList<Biglietto> lista_biglietti = new ArrayList<>();
        
        public Messaggio(ArrayList<Biglietto> lista_biglietti) {
            this.lista_biglietti = lista_biglietti;
        }
        public Messaggio (){}
    
        public ArrayList<Biglietto> getLista_biglietti() {
            return lista_biglietti;
        }
    
        public void setLista_biglietti(ArrayList<Biglietto> lista_biglietti) {
            this.lista_biglietti = lista_biglietti;
        }
}
