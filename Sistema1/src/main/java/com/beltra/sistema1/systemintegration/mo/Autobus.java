package com.beltra.sistema1.systemintegration.mo;

import com.beltra.sistema1.domain.AutobusEntity;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.springframework.stereotype.Component;

@Component
@XmlType(propOrder = {"codice", "targa", "modello", "capienza"})
@XmlRootElement(name = "autobus")
public class Autobus extends AutobusEntity {

    String codice;

    @XmlAttribute(name = "targa")
    @Override
    public String getTarga() {
        return super.getTarga();
    }

    @XmlAttribute(name = "modello")
    @Override
    public String getModello() {
        return super.getModello();
    }

    @XmlAttribute(name = "capienza")
    @Override
    public int getCapienza() {
        return super.getCapienza();
    }


    @XmlID
    @XmlAttribute(name = "codice")
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }
}
