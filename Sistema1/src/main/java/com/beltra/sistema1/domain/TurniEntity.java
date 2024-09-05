package com.beltra.sistema1.domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "turni", schema = "public", catalog = "ditta_trasporti")
public class TurniEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "data", nullable = false)
    private Date data;
    @Basic
    @Column(name = "ora_inizio", nullable = false)
    private Time oraInizio;
    @Basic
    @Column(name = "ora_fine", nullable = false)
    private Time oraFine;
    @Basic
    @Column(name = "targa", nullable = false, length = 10)
    private String targa;
    @Basic
    @Column(name = "num_linea", nullable = false)
    private int numLinea;

    @Basic
    @Column(name = "id_utente", nullable = false)
    private int idUtente;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @XmlElement(name="ora_inizio", namespace = "http://www.progetto.com/turno")
    public Time getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(Time oraInizio) {
        this.oraInizio = oraInizio;
    }

    @XmlElement(name="ora_fine", namespace = "http://www.progetto.com/turno")
    public Time getOraFine() {
        return oraFine;
    }

    public void setOraFine(Time oraFine) {
        this.oraFine = oraFine;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    @XmlTransient
    public int getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(int numLinea) {
        this.numLinea = numLinea;
    }

    @XmlTransient
    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurniEntity that = (TurniEntity) o;
        return id == that.id && numLinea == that.numLinea && idUtente == that.idUtente && Objects.equals(data, that.data) && Objects.equals(oraInizio, that.oraInizio) && Objects.equals(oraFine, that.oraFine) && Objects.equals(targa, that.targa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, oraInizio, oraFine, targa, numLinea, idUtente);
    }
}
