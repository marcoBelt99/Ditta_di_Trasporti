package com.beltra.sistema2.model.dao.XMLImpl;

import com.beltra.sistema2.model.mo.Turno;
import com.beltra.sistema2.service.AutistaService;
import com.beltra.sistema2.service.AutobusService;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAOXMLHandler extends DefaultHandler {

    private List<Turno> turni = null;

    private Turno turno = null;

    private StringBuilder data = null;

    public List<Turno> getTurni() {
        return turni;
    }

    public void setTurni(List<Turno> turni) {
        this.turni = turni;
    }

    boolean isInTurniSection = false; // SE MI TROVO NEL TAG <d:turni> ... </d:turni>
    boolean isInTurno = false; // SE MI TROVO NEL TAG <tur:turno> ... </turno>

    String codiceAutista = "";
    String codiceAutobus = "";

    @Override
    public void startDocument() throws SAXException {
        turni = new ArrayList<>();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if( "d:turni".equalsIgnoreCase(qName) )
            isInTurniSection = true;
        else if ("tur:turno".equalsIgnoreCase(qName) && isInTurniSection) {
            isInTurno = true;
            turno = new Turno();

            // Prima leggo gli attributi:
            turno.setId( Integer.parseInt( attributes.getValue("id") ) );
        }

        // Salvo il valore dell'attributo codice dei due tag <tur:autista> e <tur:autobus>
        else if("tur:autista".equalsIgnoreCase(qName)  && isInTurno)
                codiceAutista = attributes.getValue("codice");
        else if("tur:autobus".equalsIgnoreCase(qName) && isInTurniSection && isInTurno)
            codiceAutobus = attributes.getValue("codice");
        else
            data = new StringBuilder();
        }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("d:turni".equalsIgnoreCase(qName))
            isInTurniSection = false;
        else if("tur:turno".equalsIgnoreCase(qName) && isInTurno ) {
            turni.add( turno );
            isInTurno = false;
        }
        else if( isInTurno ) {
            switch (qName) {
                case "tur:data":
                turno.setData( Date.valueOf( data.toString()));
                break;
                case "tur:ora_inizio":
                    turno.setOraInizio( Time.valueOf( data.toString() ) );
                    break;
                case "tur:ora_fine":
                    turno.setOraFine( Time.valueOf( data.toString() ) );
                    break;
                case "tur:autista":
                turno.setAutista( AutistaService.getAutistiFromXML()
                        .stream()
                        .filter( autista ->  autista.getCodice().equals( codiceAutista ))
                        .findFirst()
                        .get()
                    );
                    break;
                case "tur:autobus":
                turno.setAutobus( AutobusService.getAutobusList()
                        .stream()
                        .filter( autobus ->  autobus.getCodice().equals( codiceAutobus ))
                        .findFirst()
                        .get()
                    );
                    break;
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