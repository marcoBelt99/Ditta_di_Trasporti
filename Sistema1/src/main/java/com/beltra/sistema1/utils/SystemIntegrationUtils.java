package com.beltra.sistema1.utils;

import com.beltra.sistema1.domain.AutistiEntity;
import com.beltra.sistema1.domain.AutobusEntity;
import com.beltra.sistema1.domain.LineeEntity;
import com.beltra.sistema1.domain.TurniEntity;
import com.beltra.sistema1.service.AutobusService;
import com.beltra.sistema1.service.LineeService;
import com.beltra.sistema1.service.TurniService;
import com.beltra.sistema1.service.UtentiService;
import com.beltra.sistema1.systemintegration.mo.Autista;
import com.beltra.sistema1.systemintegration.mo.Autobus;
import com.beltra.sistema1.systemintegration.mo.Linea;
import com.beltra.sistema1.systemintegration.mo.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SystemIntegrationUtils {

    @Autowired
    private  UtentiService utentiService;

    @Autowired
    private  LineeService lineeService;

    @Autowired
    private  AutobusService autobusService;

    @Autowired
    private  TurniService turniService;


    // TODO: effettuare il code injection per poter avere l'IoC sei Service di Spring
    //       utilizzo di vari costruttori.

    public SystemIntegrationUtils(UtentiService utentiService, LineeService lineeService,
                                  AutobusService autobusService, TurniService turniService) {
        this.utentiService = utentiService;
        this.lineeService = lineeService;
        this.autobusService = autobusService;
        this.turniService = turniService;
    }


    public SystemIntegrationUtils(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    public SystemIntegrationUtils(LineeService lineeService) {
        this.lineeService = lineeService;
    }

    public SystemIntegrationUtils(AutobusService autobusService) {
        this.autobusService = autobusService;
    }

    public SystemIntegrationUtils(TurniService turniService) {
        this.turniService = turniService;
    }

    public SystemIntegrationUtils() {

    }

    public  List<Autista> generaListaAutistiDaEsportare() {

        List<AutistiEntity> listaAutistiSorgente = utentiService.getListaAutisti();

        // Creo la nuova lista di autisti customizzata, partendo dalla lista sorgente
        List<Autista> listaAutistiDestinazione = listaAutistiSorgente
                .stream()
                .map(autistaSorgente -> {
                    Autista autistaDestinazione = new Autista();
                    autistaDestinazione.setCodice( "A00" + autistaSorgente.getIdUtente() );
                    autistaDestinazione.setMatricola( autistaSorgente.getMatricola() );
                    autistaDestinazione.setNome( autistaSorgente.getNome() );
                    autistaDestinazione.setCognome( autistaSorgente.getCognome() );
                    autistaDestinazione.setTelefono( autistaSorgente.getTelefono() );
                    return autistaDestinazione;
                })
                .toList();

        return listaAutistiDestinazione;
    }



    public  List<Linea> generaListaLineeDaEsportare() {
        List<LineeEntity> listaLineeSorgente = lineeService.getListaLinee();

        List<Linea> listaLineeDestinazione = listaLineeSorgente
                .stream()
                .map( lineaSorgente -> {
                    Linea lineaDestinazione = new Linea();
                    lineaDestinazione.setCodice( "L00" + lineaSorgente.getNumLinea() );
                    lineaDestinazione.setNumLinea( lineaSorgente.getNumLinea() );
                    lineaDestinazione.setDestinazione( lineaSorgente.getDestinazione() );
                    return lineaDestinazione;
                })
                .toList();

        return listaLineeDestinazione;

    }


    public  List<Autobus> generaListaAutobusDaEsportare() {

        List<AutobusEntity> listaAutobusSorgente = autobusService.getListaAutobus();

        // AtomicInteger cont = new AtomicInteger(); // per creare il codice che si autoincrementa
        // cont.getAndIncrement(); // per farlo partire da 1

        // TODO: contare quanti record ci sono a DB e assegnare questo numero a cont
        //final long[] cont = {autobusService.countAutobus()};

        AtomicInteger cont;
        if(listaAutobusSorgente.size() == 0)
            cont = new AtomicInteger( 1 );
        else
            cont = new AtomicInteger( listaAutobusSorgente.size()+1 );

        // Creo la nuova lista di autisti customizzata, partendo dalla lista sorgente
        List<Autobus> listaAutobusDestinazione = listaAutobusSorgente
                .stream()
                .map( autobusSorgente -> {
                    Autobus autobusDestinazione = new Autobus();
                    autobusDestinazione.setCodice( "B00" + (cont.getAndIncrement()) );
                    autobusDestinazione.setTarga( autobusSorgente.getTarga() );
                    autobusDestinazione.setModello( autobusSorgente.getModello() );
                    autobusDestinazione.setCapienza( autobusSorgente.getCapienza() );
                    return autobusDestinazione;
                })
                .toList();

        return listaAutobusDestinazione;
    }


    public  List<Turno> generaListaTurniDaEsportare() {

        List<TurniEntity> listaTurniSorgente = turniService.getListaTurni();

        List<Autista> listaAutisti = generaListaAutistiDaEsportare();
        List<Linea> listaLinee = generaListaLineeDaEsportare();
        List<Autobus> listaAutobus = generaListaAutobusDaEsportare();


        // Creo la nuova lista di autisti customizzata, partendo dalla lista sorgente
        List<Turno> listaTurniDestinazione = listaTurniSorgente
                .stream()
                .map(turnoSorgente -> {
                    Turno turnoDestinazione = new Turno();
                    turnoDestinazione.setId( turnoSorgente.getId() );
                    turnoDestinazione.setData( turnoSorgente.getData() );
                    turnoDestinazione.setOraInizio( turnoSorgente.getOraInizio() );
                    turnoDestinazione.setOraFine( turnoSorgente.getOraFine() );

                    turnoDestinazione.setCodiceAutista(
                            listaAutisti
                                .stream()
                                .filter(
                                    autista -> autista.getMatricola()
                                    .equals(
                                    utentiService.getAutistaById( turnoSorgente.getIdUtente()).getMatricola()
                                    )
                                )
                                .toList()
                                .getFirst()
                                .getCodice()
                    );

                    turnoDestinazione.setCodiceLinea(
                            listaLinee
                                .stream()
                                .filter (linea -> linea.getNumLinea() == turnoSorgente.getNumLinea() )
                                .toList()
                                .getFirst()
                                .getCodice()
                    );

                    turnoDestinazione.setCodiceAutobus(
                            listaAutobus
                                .stream()
                                .filter(  autobus -> autobus.getTarga().equals( turnoSorgente.getTarga() ) )
                                .toList()
                                .getFirst()
                                .getCodice()
                    );
                    return turnoDestinazione;
                })
                .toList();

        return listaTurniDestinazione;
    }

}
