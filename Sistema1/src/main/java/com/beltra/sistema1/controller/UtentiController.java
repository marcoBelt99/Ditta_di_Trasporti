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
@RequestMapping(value = "/ditta/api")
public class UtentiController {

    @Autowired
    private UtentiService utentiService;

    @RequestMapping(value = "/autisti/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE
                    )
    public AutistiEntity getAutista(@PathVariable("id") int id)
    {
        return
                ( (id != 1)  ? utentiService.getAutistaById(id) : new AutistiEntity() );
    }



    @RequestMapping( value = "/autisti/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void aggiornaUtente(@PathVariable("id") int id,
                               @RequestBody InputUtente datiUtente) {

        if (id == datiUtente.getId()) {
            UtentiEntity utente = new UtentiEntity();
            AutistiEntity autista = new AutistiEntity();

            utentiService.popolaUtenteInput(datiUtente, utente, autista);

            System.out.println("\n[Aggiornamento]: fine popolamento utente - autista\n");

            utentiService.aggiornaUtente(id, utente, autista);
        }
        else {
            System.out.println("Impossibile aggiornare l'utente");
        }

    }


}


