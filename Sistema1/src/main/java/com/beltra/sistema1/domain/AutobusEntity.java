package com.beltra.sistema1.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "autobus", schema = "public", catalog = "ditta_trasporti")
public class AutobusEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "targa", nullable = false, length = 10)
    private String targa;
    @Basic
    @Column(name = "modello", nullable = false, length = 20)
    private String modello;
    @Basic
    @Column(name = "capienza", nullable = false)
    private int capienza;

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutobusEntity that = (AutobusEntity) o;
        return capienza == that.capienza && Objects.equals(targa, that.targa) && Objects.equals(modello, that.modello);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targa, modello, capienza);
    }
}
