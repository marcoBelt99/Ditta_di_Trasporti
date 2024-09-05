package com.beltra.sistema1.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "utenti", schema = "public", catalog = "ditta_trasporti")
public class UtentiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "username", nullable = false, length = 30)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 30)
    private String password;
    @Basic
    @Column(name = "ruolo", nullable = false, length = 7)
    private String ruolo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtentiEntity that = (UtentiEntity) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(ruolo, that.ruolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, ruolo);
    }
}
