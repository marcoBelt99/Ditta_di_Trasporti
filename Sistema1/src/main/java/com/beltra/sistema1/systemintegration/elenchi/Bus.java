package com.beltra.sistema1.systemintegration.elenchi;

import com.beltra.sistema1.systemintegration.mo.Autobus;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "bus")
public class Bus {

    List<Autobus> listaAutobus;

    @XmlElement(name="autobus")
    public List<Autobus> getListaAutobus() {
        return listaAutobus;
    }


    public void setListaAutobus(List<Autobus> listaAutobus) {
        this.listaAutobus = listaAutobus;
    }
}
