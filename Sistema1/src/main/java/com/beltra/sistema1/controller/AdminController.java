package com.beltra.sistema1.controller;

import com.beltra.sistema1.domain.AutistiEntity;
import com.beltra.sistema1.domain.UtentiEntity;
import com.beltra.sistema1.service.UtentiService;
import com.beltra.sistema1.utils.InputUtente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ditta/api/admin")
public class AdminController {

    @Autowired
    UtentiService utentiService;

    @RequestMapping ( value = "/utenti",
                      method = RequestMethod.GET,
                      produces = MediaType.APPLICATION_JSON_VALUE
                    )
    public List<AutistiEntity> getListaAutisti() {

        return utentiService.getListaAutisti();
    }

    @GetMapping (
                    value = "/utenti/cognome/{surname}",
                    produces = MediaType.APPLICATION_JSON_VALUE
                )
    public List<AutistiEntity> getListaAutistiByCognome(@PathVariable("surname") String cognome)
    {
        return utentiService.getListaAutistiByCognome(cognome);
    }


    @GetMapping (value = "/utenti/lastid", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getUltimoIdUtente() {
        return ( utentiService.getUltimoId() + "" );
    }

    /*
    @RequestMapping(value = "/utenti/admin",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE
                   )

    public void amministratore( @RequestBody UtentiEntity utente) {

        if (utentiService.isAdmin(utente) )
            // ADMIN
            ;

    }
    */

    @RequestMapping( value = "/utenti",
                     method = RequestMethod.POST,
                     produces = MediaType.APPLICATION_JSON_VALUE,
                     consumes = MediaType.APPLICATION_JSON_VALUE
                    )
    @ResponseStatus(HttpStatus.CREATED)
    public void inserisciAutente(@RequestBody InputUtente datiUtente)  {

        UtentiEntity utente = new UtentiEntity();
        AutistiEntity autista = new AutistiEntity();

        utentiService.popolaUtenteInput(datiUtente, utente, autista);

        System.out.println("\n[Inserimento]: fine popolamento utente - autista\n");

        utentiService.inserisciUtente( utente, autista );

    }

    @RequestMapping( value = "/utenti/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaUtente(@PathVariable("id") int id) {

        utentiService.eliminaUtente(id);

    }

    @RequestMapping( value = "/utenti/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void aggiornaUtente(@PathVariable("id") int id,
                               @RequestBody InputUtente datiUtente) {

        UtentiEntity utente = new UtentiEntity();
        AutistiEntity autista = new AutistiEntity();

        utentiService.popolaUtenteInput(datiUtente, utente, autista);

        System.out.println("\n[Aggiornamento]: fine popolamento utente - autista\n");

        utentiService.aggiornaUtente(id, utente, autista);

    }



}
