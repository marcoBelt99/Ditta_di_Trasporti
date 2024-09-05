package com.beltra.sistema1.domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.Objects;

@Entity
@Table(name = "linee", schema = "public", catalog = "ditta_trasporti")
public class LineeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "num_linea", nullable = false)
    private int numLinea;
    @Basic
    @Column(name = "destinazione", nullable = false, length = 30)
    private String destinazione;

    public int getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(int numLinea) {
        this.numLinea = numLinea;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineeEntity that = (LineeEntity) o;
        return numLinea == that.numLinea && Objects.equals(destinazione, that.destinazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numLinea, destinazione);
    }
}
