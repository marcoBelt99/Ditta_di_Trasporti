package com.beltra.sistema1.systemintegration.mo.turni;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="linea")
public class LineaTurno {
    private String codice;

    public LineaTurno() {
    }

    @XmlAttribute(name="codice")
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }
}
