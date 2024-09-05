package com.beltra.sistema2.model.dao.XMLImpl;

import com.beltra.sistema2.model.mo.Autista;
import com.beltra.sistema2.model.mo.Retribuzione;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class AutistaDAOXMLHandler extends DefaultHandler {

    /** Lista */
    private List<Autista> autisti = null;

    /** Oggetto */
    private Autista autista = null;



    /** per leggere il contenuto dei tag */
    private StringBuilder data = null;

//    private GregorianCalendar dataTurno = null; // quando incontro una data

    /* Getters setters */

    public List<Autista> getAutisti() {
        return autisti;
    }

    public void setAutisti(List<Autista> autisti) {
        this.autisti = autisti;
    }

    /** Flag */
    boolean isInAutistiSection = false; // SE MI TROVO NEL TAG <d:autisti> ... </d:autisti>
    boolean isInAutista = false; // SE MI TROVO NEL TAG <d:autista> ... </d:autista>


    // Parte il documento e inizializzo l'array

    @Override
    public void startDocument() throws SAXException {
        autisti = new ArrayList<>();

    }

    /** Azioni da fare quando incontri un determinato elemento */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        /** Quando incontro "d:autisti" */
        if(qName.equalsIgnoreCase("d:autisti"))
                isInAutistiSection = true;
        else if("d:autista".equalsIgnoreCase(qName) && isInAutistiSection) {
            isInAutista = true;
            autista = new Autista();
            // Prima leggo gli attributi:
            autista.setCodice( attributes.getValue("codice") );
            autista.setMatricola( attributes.getValue("matricola") );
        }
        else
            data = new StringBuilder(); // Creo il contenitore per i dati
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if("d:autisti".equalsIgnoreCase(qName))
            isInAutistiSection = false;
        else if ("d:autista".equalsIgnoreCase(qName) && isInAutista ) {
            autisti.add( autista );
            isInAutista = false;
        }
        else if (isInAutista) {
            switch (qName) {
                case "common:nome":
                    autista.setNome(data.toString());
                    break;
                case "common:cognome":
                    autista.setCognome(data.toString());
                    break;
                case "common:telefono":
                    autista.setTelefono(data.toString());
            }
        }
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
