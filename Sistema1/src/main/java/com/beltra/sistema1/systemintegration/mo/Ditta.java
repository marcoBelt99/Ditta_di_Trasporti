package com.beltra.sistema1.systemintegration.mo;

import com.beltra.sistema1.domain.AutistiEntity;
import com.beltra.sistema1.domain.AutobusEntity;
import com.beltra.sistema1.domain.LineeEntity;
import com.beltra.sistema1.domain.TurniEntity;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


import java.util.List;


@XmlRootElement
@XmlType(propOrder = { "nome", "indirizzo", "descrizione", "autisti", "linee", "bus", "turni" })  // Specifica l'ordine degli elementi
public class Ditta {

    // Attributi
    private String nome;

    private Indirizzo indirizzo;

    private String descrizione;


    private List<Autista> autisti;

    private List<Linea> linee;

    private List<Autobus> bus;

   // private List<TurniEntity> turni;
    private List<Turno> turni;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }



    // TODO: con @XmlElementWrapper posso dire che voglio un elemento <autisti> che contiene
    //  una serie di elementi <autista>.
    //  Con @XmlElemen(name="autista") specifico il nome degli elementi contenuti
    @XmlElementWrapper
    @XmlElement(name = "autista")
    public List<Autista> getAutisti() {
        return autisti;
    }

    public void setAutisti(List<Autista> autisti) {
        this.autisti = autisti;
    }



    @XmlElementWrapper
    @XmlElement(name = "linea")
    public List<Linea> getLinee() {
        return linee;
    }

    public void setLinee(List<Linea> linee) {
        this.linee = linee;
    }



    // NB:il contenitore è chiamato <bus>, gli elementi al suo interno sono <autobus>
    @XmlElementWrapper
    @XmlElement(name="autobus")
    public List<Autobus> getBus() {
        return bus;
    }

    public void setBus(List<Autobus> bus) {
        this.bus = bus;
    }



//    public List<TurniEntity> getTurni() {
//        return turni;
//    }

//    public void setTurni(List<TurniEntity> turni) {
//        this.turni = turni;
//    }


    @XmlElementWrapper
    @XmlElement(name="turno", namespace = "http://www.progetto.com/turno")
    public List<Turno> getTurni() {
        return turni;
    }

    public void setTurni(List<Turno> turni) {
        this.turni = turni;
    }
}
