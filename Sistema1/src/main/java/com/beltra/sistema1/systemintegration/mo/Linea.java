package com.beltra.sistema1.systemintegration.mo;


import com.beltra.sistema1.domain.LineeEntity;
import com.beltra.sistema1.service.LineeServiceImpl;
import jakarta.xml.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@XmlType(propOrder = {"codice", "num_linea", "destinazione"})
@XmlRootElement(name = "linea")
public class Linea extends LineeEntity {

    @XmlTransient
    @Autowired
    private LineeServiceImpl lineeServiceImpl;

    private String codice;


    @Override
    @XmlAttribute(name = "num_linea")
    public int getNumLinea() {
        return super.getNumLinea();
    }


    @XmlElement(name = "destinazione", namespace = "http://www.progetto.com/linea")
    @Override
    public String getDestinazione() {
        return super.getDestinazione();
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
