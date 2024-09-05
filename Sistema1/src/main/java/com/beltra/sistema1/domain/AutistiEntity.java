package com.beltra.sistema1.domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.Objects;

@Entity
@Table(name = "autisti", schema = "public", catalog = "ditta_trasporti")
public class AutistiEntity {
    @Basic
    @Column(name = "matricola", nullable = false, length = 5)
    private String matricola;
    @Basic
    @Column(name = "cognome", nullable = false, length = 30)
    private String cognome;
    @Basic
    @Column(name = "nome", nullable = false, length = 25)
    private String nome;
    @Basic
    @Column(name = "telefono", nullable = true, length = 10)
    private String telefono;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_utente", nullable = false)
    private int idUtente;

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        AutistiEntity that = (AutistiEntity) o;
        return idUtente == that.idUtente && Objects.equals(matricola, that.matricola) && Objects.equals(cognome, that.cognome) && Objects.equals(nome, that.nome) && Objects.equals(telefono, that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricola, cognome, nome, telefono, idUtente);
    }
}
