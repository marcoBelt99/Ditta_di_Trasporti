package com.beltra.sistema1.systemintegration.mo;

import com.beltra.sistema1.domain.AutistiEntity;
import com.beltra.sistema1.service.UtentiServiceImpl;
import jakarta.xml.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@XmlType(propOrder = {"codice","matricola", "nome", "cognome", "telefono"})
@XmlRootElement(name = "autista")
public class Autista extends AutistiEntity {

    @XmlTransient
    @Autowired
    private UtentiServiceImpl utentiServiceImpl;

    private String codice;

    @XmlID
    @XmlAttribute(name="codice")
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @XmlAttribute(name = "matricola")
    @Override
    public String getMatricola() {
        return super.getMatricola();
    }

    @XmlElement(name = "cognome", namespace = "http://www.progetto.com/common")
    @Override
    public String getCognome() {
        return super.getCognome();
    }

    @XmlElement(name = "nome", namespace = "http://www.progetto.com/common")
    @Override
    public String getNome() {
        return super.getNome();
    }

    @XmlElement(name = "telefono", namespace = "http://www.progetto.com/common")
    @Override
    public String getTelefono() {
        return super.getTelefono();
    }


    @XmlTransient
    @Override
    public int getIdUtente() {
        return super.getIdUtente();
    }


}
