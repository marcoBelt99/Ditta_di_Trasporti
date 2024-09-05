package com.beltra.sistema1.utils;

/** Classe di appoggio per essere usata in fase di inserimento di un nuovo utente,
 *  inteso come: utente + autista.
 *  <br>
 *  Sarà usata anche in fase di modifica di un utente (intesi come utente + autista).
 * */
public class InputUtente {

    private int id;

    public int getId() {
        return id;
    }

    // AUTISTA
    private String matricola;

    private String cognome;

    private String nome;

    private String telefono;

    // UTENTE
    private String username;

    private String password;

    private  String ruolo;

    public InputUtente() {

    }

    public InputUtente(String matricola, String cognome, String nome, String telefono, String username, String password) {
        this.matricola = matricola;
        this.cognome = cognome;
        this.nome = nome;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
    }

    // getters
    public String getMatricola() {
        return matricola;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getRuolo() {
        return ruolo;

    }

    public void setRuolo() {
        this.ruolo = Stringhe.AUTISTA;
    }
}
