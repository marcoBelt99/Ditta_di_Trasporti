package com.beltra.sistema1.systemintegration.si;

import com.beltra.sistema1.domain.LineeEntity;
import com.beltra.sistema1.service.LineeService;
import com.beltra.sistema1.service.LineeServiceImpl;
import com.beltra.sistema1.systemintegration.elenchi.Linee;
import com.beltra.sistema1.systemintegration.mo.Linea;
import com.beltra.sistema1.utils.SystemIntegrationUtils;
import jakarta.xml.bind.JAXB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.List;

@Component
public class SystemIntegrationLinea implements SystemIntegration {

    @Autowired
    private LineeService lineeService;

    private Linee linee;

    SystemIntegrationUtils sysIntegrUtils;

//    private List<Linea> generaListaLineeDaEsportare() {
//        List<LineeEntity> listaLineeSorgente = lineeService.getListaLinee();
//
//        List<Linea> listaLineeDestinazione = listaLineeSorgente
//                .stream()
//                .map( lineaSorgente -> {
//                    Linea lineaDestinazione = new Linea();
//                    lineaDestinazione.setCodice( "L00" + lineaSorgente.getNumLinea() );
//                    lineaDestinazione.setNumLinea( lineaSorgente.getNumLinea() );
//                    lineaDestinazione.setDestinazione( lineaSorgente.getDestinazione() );
//                    return lineaDestinazione;
//                })
//                .toList();
//
//        return listaLineeDestinazione;
//
//    }


    @Override
    public void produciXML() {

        // Necessario per richiamare la funzione di creazione della lista da esportare
        sysIntegrUtils = new SystemIntegrationUtils( lineeService );

        // Creo un nuovo elenco di Linea che ha come root la lista
        linee = new Linee();

        linee.setListaLinee( sysIntegrUtils.generaListaLineeDaEsportare() );

        // StringWriter per stampare a video
        StringWriter sw = new StringWriter();

        // TODO: conversione da oggetto Java ad XML

        JAXB.marshal(linee, sw);

        // Visualizzo a video l'XML generato
        System.out.println(sw);
    }


}


