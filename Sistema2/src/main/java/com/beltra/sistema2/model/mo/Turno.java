package com.beltra.sistema2.model.mo;

import java.sql.Date;
import java.sql.Time;



public class Turno {

    private int id;

    private Date data;

    private Time oraInizio;

    private Time oraFine;

    private Autista autista;

    private Autobus autobus;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(Time oraInizio) {
        this.oraInizio = oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }

    public void setOraFine(Time oraFine) {
        this.oraFine = oraFine;
    }

    public Autista getAutista() {
        return autista;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }


    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", data=" + data +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + oraFine +
                ", autista=" + autista.getCodice() +
                ", autobus=" + autobus.getCodice() +
                '}';
    }
}
