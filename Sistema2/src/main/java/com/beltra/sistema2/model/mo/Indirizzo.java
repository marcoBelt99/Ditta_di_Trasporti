package com.beltra.sistema2.model.mo;

public class Indirizzo {
    private String via;

    private String cap;

    private String citta;

    private Provincia provincia;


    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }


    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }


    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }


}

