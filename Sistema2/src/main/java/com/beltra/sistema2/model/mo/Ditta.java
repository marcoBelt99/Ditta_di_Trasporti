package com.beltra.sistema2.model.mo;


import java.util.List;


public class Ditta {

    // Attributi
    private String nome;

    private Indirizzo indirizzo;

    private String descrizione;


    private List<Autista> autisti;

    private List<Linea> linee;

    private List<Autobus> bus;


    private List<Turno> turni;

    // Getters e setters
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

    public List<Autista> getAutisti() {
        return autisti;
    }

    public void setAutisti(List<Autista> autisti) {
        this.autisti = autisti;
    }

    public List<Linea> getLinee() {
        return linee;
    }

    public void setLinee(List<Linea> linee) {
        this.linee = linee;
    }

    public List<Autobus> getBus() {
        return bus;
    }

    public void setBus(List<Autobus> bus) {
        this.bus = bus;
    }

    public List<Turno> getTurni() {
        return turni;
    }

    public void setTurni(List<Turno> turni) {
        this.turni = turni;
    }

    @Override
    public String toString() {
        return "Ditta{" +
                "nome='" + nome + '\'' +
                ", indirizzo=" + indirizzo +
                ", descrizione='" + descrizione + '\'' +
                ", autisti=" + autisti +
                ", linee=" + linee +
                ", bus=" + bus +
                ", turni=" + turni +
                '}';
    }
}
