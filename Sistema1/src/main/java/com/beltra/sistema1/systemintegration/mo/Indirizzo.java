package com.beltra.sistema1.systemintegration.mo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "via", "cap", "citta", "provincia" }) // Specifica l'ordine degli elementi
public class Indirizzo {
    private String via;

    private String cap;

    private String citta;

    private Provincia provincia;

    @XmlElement(name = "via")
    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    @XmlElement(name = "cap")
    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    @XmlElement(name = "citta")
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

