package com.beltra.sistema1.controller;

import com.beltra.sistema1.systemintegration.si.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ditta/api")
public class SystemIntegrationController {


    @Autowired
    private SystemIntegrationDitta systemIntegrationDitta;

    @Autowired
    private SystemIntegrationAutista systemIntegrationAutista;


    @Autowired
    private SystemIntegrationLinea systemIntegrationLinea;

    @Autowired
    private SystemIntegrationAutobus systemIntegrationAutobus;


    @Autowired
    private SystemIntegrationTurni systemIntegrationTurni;


    /** TODO: Utilizzo di una generic operation con lo scopo di produrre un file XML.
     * TODO: Si usa un path del tipo /contesto/metodo
     *
     * */
    @RequestMapping(value = "/systemintegration/producixml",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    // @ResponseStatus()
    public void produciXML() {

        systemIntegrationDitta.produciXML();

    }


    /** TODO: Utilizzo di altre generic operations. */
    @RequestMapping(value = "/systemintegration/autisti/producixml",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public void produciXMLAutista() {

        systemIntegrationAutista.produciXML();

    }



    @RequestMapping(value = "/systemintegration/linee/producixml",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public void produciXMLLinea() {
        systemIntegrationLinea.produciXML();
    }



    @RequestMapping(value = "/systemintegration/bus/producixml",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public void produciXMLAutobus() {
        systemIntegrationAutobus.produciXML();
    }



    @RequestMapping(value = "/systemintegration/turni/producixml",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public void produciXMLTurni() {
        systemIntegrationTurni.produciXML();
    }


    /** TODO: Uso di una generic operation per mandare tramite socket il file ad un client */
    @RequestMapping(value = "/systemintegration/ditta/inviaxml",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public void inviaXML() {
        systemIntegrationDitta.inviaXML();
    }


    @RequestMapping(value = "/systemintegration/bus/inviaxml",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public void inviaXMLBus() {
        systemIntegrationAutobus.inviaXML();
    }


    @RequestMapping(value = "/systemintegration/autisti/inviaxml",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public void inviaXMLAutisti() {
        systemIntegrationAutista.inviaXML();
    }


    @RequestMapping(value = "/systemintegration/turni/inviaxml",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public void inviaXMLTurni() {
        systemIntegrationTurni.inviaXML();
    }

}
