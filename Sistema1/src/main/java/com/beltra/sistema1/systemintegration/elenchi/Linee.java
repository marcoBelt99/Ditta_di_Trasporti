package com.beltra.sistema1.systemintegration.elenchi;

import com.beltra.sistema1.systemintegration.mo.Autista;
import com.beltra.sistema1.systemintegration.mo.Linea;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "linee")
public class Linee {

    private List<Linea> listaLinee;

    @XmlElement(name = "linea")
    public List<Linea> getListaLinee() {
        return listaLinee;
    }

    public void setListaLinee(List<Linea> listaLinee) {
        this.listaLinee = listaLinee;
    }
}
