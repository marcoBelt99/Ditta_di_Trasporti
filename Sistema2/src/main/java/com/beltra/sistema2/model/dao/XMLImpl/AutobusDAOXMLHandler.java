package com.beltra.sistema2.model.dao.XMLImpl;

import com.beltra.sistema2.model.mo.Autista;
import com.beltra.sistema2.model.mo.Autobus;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class AutobusDAOXMLHandler extends DefaultHandler {

    /** Lista */
    private List<Autobus> autobusList = null;

    /**  Oggetto */
    private Autobus autobus = null;

    /** per leggere il contenuto dei tag */

    private StringBuilder data = null;

    /* Getter e setter della lista */
    public List<Autobus> getAutobusList() {
        return autobusList;
    }

    public void setAutobusList(List<Autobus> autobusList) {
        this.autobusList = autobusList;
    }


    /** Flag */
    boolean isInBusSection = false; // SE MI TROVO NEL TAG <d:bus> ... </d:bus>
    boolean isInAutobus = false; // SE MI TROVO NEL TAG <d:autobus> ... </d:autobus>

    // Parte il documento e inizializzo l'array


    @Override
    public void startDocument() throws SAXException {
        autobusList = new ArrayList<>();
    }

    /** Azioni da fare quando incontri un determinato elemento */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("d:bus"))
            isInBusSection = true;
        else if ("d:autobus".equalsIgnoreCase(qName) && isInBusSection) {
            isInAutobus = true;
            autobus = new Autobus();
            // Prima leggo gli attributi
            autobus.setCodice( attributes.getValue("codice") );
            autobus.setTarga(attributes.getValue("targa") );
            autobus.setModello( attributes.getValue("modello") );
            autobus.setCapienza( Integer.parseInt( attributes.getValue("capienza") ) );
        }
        else
            data = new StringBuilder(); // Creo lo stringbuilder per leggere tutti i caratteri
    }

    /** In fase di fine tag vado a leggere i contenuti (se presenti) con lo StringBuffer.
     *  <br>In questo caso non sono presenti valori per il tag <\d:autobus></d:autobus>
     *  <br>Quindi non uso lo StringBuffer
     *  */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("d:bus".equalsIgnoreCase(qName))
            isInBusSection = false;
        else if("d:autobus".equalsIgnoreCase(qName) && isInAutobus){
            autobusList.add( autobus );
            isInAutobus = false;
        }
        else
            ;
    }


    /** Quando il parser SAX legge il contenuto, è chiamato il metodo characters, che fa una append sulla stringa
     *  data di quello che mi leggo */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append( ch, start, length );
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.err.println("Fatal Error: " + e);
        throw new SAXException(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.err.println("Error: " + e);
        throw new SAXException(e);
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.err.println("Warning: " + e);
        throw new SAXException(e);
    }
}
