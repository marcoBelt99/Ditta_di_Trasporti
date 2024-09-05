package com.beltra.sistema1.systemintegration.elenchi;

import com.beltra.sistema1.systemintegration.mo.Autista;
import com.beltra.sistema1.systemintegration.mo.Turno;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "turni")
public class Turni {

    private List<Turno> listaTurni;

    @XmlElement(name="turno", namespace = "http://www.progetto.com/turno")
    public List<Turno> getListaTurni() {
        return listaTurni;
    }

    public void setListaTurni(List<Turno> listaTurni) {
        this.listaTurni = listaTurni;
    }
}
