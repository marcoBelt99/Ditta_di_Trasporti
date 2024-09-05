package com.beltra.sistema1.systemintegration.mo;

import com.beltra.sistema1.domain.TurniEntity;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.springframework.stereotype.Component;

import com.beltra.sistema1.systemintegration.mo.turni.*;

import java.sql.Date;
import java.sql.Time;

@Component
@XmlType(propOrder = {"data", "oraInizio", "oraFine", "autista", "linea", "autobus"})
// ho usato "oraInizio" anziche' "ora_inizio" per problemi con @XmlType. Stesso motivo per "oraFine" con "ora_fine"
@XmlRootElement(name= "turno", namespace = "http://www.progetto.com/turno")
public class Turno extends TurniEntity {

    private AutistaTurno autista;
    private final LineaTurno linea;
    private final AutobusTurno autobus;

    public Turno() {
        autista = new AutistaTurno();
        linea = new LineaTurno();
        autobus = new AutobusTurno();
    }

    @XmlAttribute(name="id")
    @Override
    public long getId() {
        return super.getId();
    }

    @XmlElement(name="data", namespace = "http://www.progetto.com/turno")
    //@XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @Override
    public Date getData() {
        return super.getData();
    }

    @XmlElement(name="ora_inizio", namespace = "http://www.progetto.com/turno")
    //@XmlSchemaType(name = "time")
    @XmlJavaTypeAdapter(TimeAdapter.class)
    @Override
    public Time getOraInizio() {
        return super.getOraInizio();
    }

    @XmlElement(name="ora_fine", namespace = "http://www.progetto.com/turno")
    //@XmlSchemaType(name = "time")
    @XmlJavaTypeAdapter(TimeAdapter.class)
    @Override
    public Time getOraFine() {
        return super.getOraFine();
    }


    @XmlElement(name="autista", namespace = "http://www.progetto.com/turno")
    public AutistaTurno getAutista() { return autista; }


    @XmlElement(name = "linea", namespace = "http://www.progetto.com/turno")
    public LineaTurno getLinea() {
        return linea;
    }


    @XmlElement(name="autobus", namespace = "http://www.progetto.com/turno")
    public AutobusTurno getAutobus() {
        return autobus;
    }


    @XmlTransient
    public String getCodiceAutista() {
        return this.getAutista().getCodice();
    }
    @XmlTransient
    public String getCodiceLinea() {
        return this.getAutista().getCodice();
    }
    @XmlTransient
    public String getCodiceAutobus() {
        return this.getLinea().getCodice();
    }


    public void setCodiceAutista(String codice) {
        this.getAutista().setCodice(codice);
    }


    public void setCodiceLinea(String codice) {
        this.getLinea().setCodice(codice);
    }


    public void setCodiceAutobus(String codice) {
        this.getAutobus().setCodice(codice);
    }


    public void setAutistaTurno(AutistaTurno autistaTurno) {
        this.autista = autistaTurno;
    }



    // TODO: Adattatore per java.sql.Date
    public static class DateAdapter extends XmlAdapter<String, Date> {
        @Override
        public Date unmarshal(String v) {
            return Date.valueOf(v); // Converte stringa in java.sql.Date
        }

        @Override
        public String marshal(Date v) {
            return v.toString(); // Converte java.sql.Date in stringa
        }
    }

    // TODO: Adattatore per java.sql.Time
    public static class TimeAdapter extends XmlAdapter<String, Time> {
        @Override
        public Time unmarshal(String v) {
            return Time.valueOf(v); // Converte stringa in java.sql.Time
        }

        @Override
        public String marshal(Time v) {
            return v.toString(); // Converte java.sql.Time in stringa
        }
    }



}
