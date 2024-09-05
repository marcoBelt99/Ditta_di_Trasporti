package com.beltra.sistema1.service;

import com.beltra.sistema1.domain.AutistiEntity;
import com.beltra.sistema1.domain.UtentiEntity;
import com.beltra.sistema1.repository.AutistiRepository;
import com.beltra.sistema1.repository.UtentiRepository;

import com.beltra.sistema1.utils.InputUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class UtentiServiceImpl implements UtentiService {

    @Autowired
    private AutistiRepository autistiRepository;

    @Autowired
    private UtentiRepository utentiRepository;

    @Autowired
    private TurniService turniService;


    @Override
    public Boolean isAdmin(UtentiEntity u) {
        // SE HO OTTENUTO IL PRIMO UTENTE, CON ID=1, E CON RUOLO PARI A "ADMIN"
        // ALLORA E' L'AMMINISTRATORE

        Optional<UtentiEntity> admin = utentiRepository.findById( u.getId() );

        // isPresent per verificare se effettivamente è stato pescato dalla findById
        return admin.isPresent() && (
                (admin.get().getId() == 1) && (admin.get().getRuolo().equals("ADMIN"))
        );
    }

    @Override
    public List<AutistiEntity> getListaAutisti() {

        List<AutistiEntity> listaAutisti = autistiRepository.findAll();
        // EVENTUALE ELABORAZIONE CON LA LISTA DEGLI AUTISTI
        return listaAutisti;
    }

    @Override
    public AutistiEntity getAutistaById(int id) {
        Optional<AutistiEntity> autista = autistiRepository.findById(id);

        return autista.get();
    }

    @Override
    public UtentiEntity getUtenteById(int id) {
        Optional<UtentiEntity> utente = utentiRepository.findById(id);

        if(utente.isPresent())
            return utente.get();
        else
            return null;
    }

    @Override
    public List<AutistiEntity> getListaAutistiByCognome(String cognome) {

        // return autistiRepository.findAllByCognome(cognome); // versione case sensitive
        return autistiRepository.findAllByCognomeIgnoreCase(cognome); // versione con case insensitive
    }

    @Override
    public int getUltimoId() {
        return  utentiRepository.findTopByOrderByIdDesc().getFirst().getId();
    }

    @Override
    @Transactional
    public void inserisciUtente(UtentiEntity utente, AutistiEntity autista) {
        utentiRepository.inserisciUtente( utente.getUsername(), utente.getPassword() );
        autistiRepository.inserisciAutista( autista.getMatricola(), autista.getNome(), autista.getCognome(), autista.getTelefono() );
    }

    @Override
    @Transactional
    public void eliminaUtente(int id) {

        UtentiEntity utente = getUtenteById(id);

        if( !isAdmin( utente ) )
        {
            int savedId = utente.getId();

            // TODO: Prima di eliminare un utente, devo eliminare tutti i riferimenti di quell'utente
            //  nella tabella turni...
            turniService.cancellaTuttiTurniUtente( id );
            utentiRepository.delete( utente );
            System.out.println("Eliminato utente di ID: " + savedId + "\n");
        }
        else
            System.out.println("Utente non eliminabile\n");
    }

    @Override
    @Transactional
    public void aggiornaUtente(int id, UtentiEntity utenteInput, AutistiEntity autistaInput) {

        // Ricerca di autente + autista di interesse
        UtentiEntity utente = getUtenteById(id);
        AutistiEntity autista = getAutistaById( utente.getId() );

        if( !isAdmin( utente ) )
        {
            int savedId = utente.getId();

            utentiRepository.aggiornaUtente( utente.getId(), utenteInput.getUsername(), utenteInput.getPassword() );

            autistiRepository.aggiornaAutista( autista.getIdUtente(), autistaInput.getMatricola(), autistaInput.getNome(),
                    autistaInput.getCognome(), autistaInput.getTelefono() );

            System.out.println("Aggiornato utente di ID: " + savedId + "\n");
        }
        else
            System.out.println("Utente non aggiornabile\n");
    }

    @Override
    public void popolaUtenteInput(InputUtente datiUtente, UtentiEntity utente, AutistiEntity autista) {

        datiUtente.setRuolo();

        utente.setUsername( datiUtente.getUsername() );
        utente.setPassword( datiUtente.getPassword() );
        utente.setRuolo( datiUtente.getRuolo() );

        autista.setMatricola( datiUtente.getMatricola() );
        autista.setCognome( datiUtente.getCognome() );
        autista.setNome( datiUtente.getNome() );
        autista.setTelefono( datiUtente.getTelefono() );
    }

}
