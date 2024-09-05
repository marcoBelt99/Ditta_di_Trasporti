package com.beltra.sistema2.model.mo;

import java.time.Month;
import java.util.concurrent.atomic.AtomicInteger;

public class Retribuzione {

    private int id;

    private double importo;

    private String mese;


    private Autista autista;


    public Retribuzione() {

    }

    public Retribuzione( String mese, Autista autista) {
        this.mese = mese;
        this.autista = autista;
    }

    public Retribuzione(AtomicInteger idRetribuzione, double importo, String mese, Autista autista) {
        this.id = idRetribuzione.get();
        this.importo = importo;
        this.mese = mese;
        this.autista = autista;
    }
    public Retribuzione( double importo, String mese, Autista autista) {
        this.mese = mese;
        this.autista = autista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public String getMese() {
        return mese;
    }

    public void setMese(String mese) {
        this.mese = mese;
    }

    public Autista getAutista() {
        return autista;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }

    @Override
    public String toString() {
        return "Retribuzione {" +
                "id=" + id +
                ", importo=" + importo +
                ", mese=" + mese +
                ", autista=" + autista.getNome() + " " + autista.getCognome() + " " +
                '}';
    }
}
