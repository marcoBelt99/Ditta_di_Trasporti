package com.beltra.sistema1.systemintegration.elenchi;

import com.beltra.sistema1.systemintegration.mo.Autista;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "autisti")
public class Autisti {

    private List<Autista> listaAutisti;

    @XmlElement(name = "autista")
    public List<Autista> getListaAutisti() {
        return listaAutisti;
    }

    public void setListaAutisti(List<Autista> listaAutisti) {
        this.listaAutisti = listaAutisti;
    }


}
