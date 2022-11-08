package it.fi.gf.meucci;

public class Biglietto {
    int ID;
    String numero_biglietto;
    
    public Biglietto() {
    }

    public Biglietto(int iD, String numero_biglietto) {
        ID = iD;
        this.numero_biglietto = numero_biglietto;
    }

    public  int getID() {
        return ID;
    }

    public  void setID(int iD) {
        ID = iD;
    }

    public String getNumero_biglietto() {
        return numero_biglietto;
    }

    public void setNumero_biglietto(String numero_biglietto) {
        this.numero_biglietto = numero_biglietto;
    }
}