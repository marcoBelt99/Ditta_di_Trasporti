package com.beltra.sistema1.service;

import com.beltra.sistema1.domain.TurniEntity;
import com.beltra.sistema1.repository.TurniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TurniService {
      List<TurniEntity> getListaTurni();

      /** Ritorna lo storico dei turni svolti da un determinato utente,
       *  del quale viene specificato l'id */
      List<TurniEntity> getListaTurniAutistaById(int id_utente);

    /** Ritorna lo storico dei turni svolti da un determinato utente,
     *  del quale viene specificato l'id */
    List<TurniEntity> getListaTurniAutistaByIdFilterByMese(int id_utente, String mese);

    /** Salva a DB un nuovo turno */
    void inserisciTurno(TurniEntity turno);


    /** Effettua un caricamento massivo di turni a DB  */
    void inserisciBatchTurni(List<TurniEntity> listaTurni);


    /** Elimina tutti i turni relativi ad un determinato utente.<br>
     *  Purtoppo questa funzione serve all'admin per poter eliminare
     *  un determinato utente, però l'utente, si riferisce ad 1 determinato
     *  autista (utente 1 ----> 1 autista), ma l'autista ha dei riferimenti
     *  nella tabella turni.
     * */
    void cancellaTuttiTurniUtente(int id);
}
