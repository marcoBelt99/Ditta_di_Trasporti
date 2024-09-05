package com.beltra.sistema1.service;

import com.beltra.sistema1.domain.AutistiEntity;
import com.beltra.sistema1.domain.UtentiEntity;
import com.beltra.sistema1.utils.InputUtente;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UtentiService {


    /** Ritorna la lista di tutti gli autisti presenti sul database */
    List<AutistiEntity> getListaAutisti();

    /** Ritorna un singolo autista, dato un id  */
    AutistiEntity getAutistaById(int id);

    /** Ritorna un singolo utente, dato un id  */
    UtentiEntity getUtenteById(int id);

    /** Ritorna la lista di tutti gli autisti che hanno un determinato cognome */
    List<AutistiEntity> getListaAutistiByCognome(String cognome);

    /** Recupera l'id dell'ultimo utente inserito */
   int getUltimoId();

    /** Ritorna true se l'utente passato coincide con l'amministratore  */
    Boolean isAdmin(UtentiEntity u);

    /** Inserisce prima un utente.
     * <br>
     * Successivamente inserisce un autista che ha come id_utente l'id dell'utente appena creato  */
     void inserisciUtente( UtentiEntity utente, AutistiEntity autista );


     /** Elimina un determinato autista. L'eliminazione avviene in cascade: se elimino l'utente
      * poi in cascata viene rimosso anche l'autista associato */
     void eliminaUtente( int id);

     /** Aggiorna le informazioni relative ad un determinato utente.<br>
      *  Devo distinguere, in base alle informazioni da cambiare, in
      *  quale tabella (utenti o autisti) effettuare l'update.<br>
      *  Decido in base a quali campi sono aggiornati.<br>
      *  Naturalmente, se voglio aggiornare Es) sia lo username dell'utente che
      *  il telefono dell'autista, allora interverrò in entrambe le tabelle
      * */
    void aggiornaUtente(int id, UtentiEntity utenteInput, AutistiEntity autistaInput);


    /** Popola le due entità:  {@link UtentiEntity} e {@link AutistiEntity} con i dati di {@link InputUtente}.<br>
     * I dati di {@link InputUtente} sono ricevuti dal @{@link org.springframework.web.bind.annotation.RequestBody}. */
    void popolaUtenteInput(InputUtente datiUtente, UtentiEntity utente, AutistiEntity autista);
}
