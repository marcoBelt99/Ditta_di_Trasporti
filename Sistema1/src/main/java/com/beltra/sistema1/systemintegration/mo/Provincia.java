package com.beltra.sistema1.systemintegration.mo;

import jakarta.xml.bind.annotation.XmlAttribute;

public class Provincia {


    private String codice;

    @XmlAttribute
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }
}
