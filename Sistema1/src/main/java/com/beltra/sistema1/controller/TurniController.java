package com.beltra.sistema1.controller;

import com.beltra.sistema1.domain.AutistiEntity;
import com.beltra.sistema1.domain.TurniEntity;
import com.beltra.sistema1.domain.UtentiEntity;
import com.beltra.sistema1.service.TurniService;
import com.beltra.sistema1.utils.InputUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/ditta/api")
public class TurniController {

    @Autowired
    TurniService turniService;


    @GetMapping(value = "/turni", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TurniEntity> getListaTurni() {
        return turniService.getListaTurni();
    }


    @GetMapping(value = "/turni/autisti/{id_utente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TurniEntity> getListaTurniAutistaById(
            @PathVariable("id_utente") int id_utente) {

        return turniService.getListaTurniAutistaById(id_utente);
    }

    @GetMapping(value = "/turni/autisti/{id_utente}/{mese}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TurniEntity> getListaTurniAutistaByIdFilterByMese(
            @PathVariable("id_utente" ) int id_utente,
            @PathVariable("mese" ) String mese ) {

        return turniService.getListaTurniAutistaByIdFilterByMese(id_utente, mese);
    }



    @PostMapping(value = "/turni", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String inserisciTurno(@RequestBody TurniEntity turno)  {

        turniService.inserisciTurno(turno);

        return "Turno inserito correttamente!";

    }


    @PostMapping(value = "/turni/batch", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String inserisciTurni(@RequestBody List<TurniEntity> listaTurni)  {

        turniService.inserisciBatchTurni(listaTurni);

        return "Batch di turni inserito correttamente!";

    }

}
