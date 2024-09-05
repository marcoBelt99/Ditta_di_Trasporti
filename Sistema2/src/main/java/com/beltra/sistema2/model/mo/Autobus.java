package com.beltra.sistema2.model.mo;


public class Autobus {

    private String codice;

    private String targa;

    private String modello;

    private int capienza;


    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "Autobus{" +
                "codice='" + codice + '\'' +
                ", targa='" + targa + '\'' +
                ", modello='" + modello + '\'' +
                ", capienza=" + capienza +
                '}';
    }
}
