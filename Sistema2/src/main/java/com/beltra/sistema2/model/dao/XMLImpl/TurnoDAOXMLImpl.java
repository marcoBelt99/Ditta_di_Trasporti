package com.beltra.sistema2.model.dao.XMLImpl;

import com.beltra.sistema2.model.dao.TurnoDAO;
import com.beltra.sistema2.model.mo.Autista;
import com.beltra.sistema2.model.mo.Turno;
import com.beltra.sistema2.utils.Stringhe;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TurnoDAOXMLImpl implements TurnoDAO {

    String xmlDirURL;

    public TurnoDAOXMLImpl(String xmlDirURL) {this.xmlDirURL = xmlDirURL;}


    @Override
    public List<Turno> findAll() {

        /** Istanzio un parser e uno Schema Factory*/
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        /** Recupero il file XSD */
        File fileXSD = new File(Stringhe.DIRECTORY_PROGETTO, Stringhe.FILE_DITTA_XSD);

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();

            /** Richiamo file XSD per la validazione */
            schemaFactory.newSchema(fileXSD);
            saxParserFactory.setValidating(true); // gli dico che devo fare la validazione (con xsd o con dtd. Nel mio caso xsd).
            saxParserFactory.setNamespaceAware(true); // Attivo il supporto ai namespace XML

            // Dico al parser che la validazione deve essere fatta rispetto all'XML Schema
            saxParser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema"
            );

            /**  Creo un handler */
            TurnoDAOXMLHandler handler = new TurnoDAOXMLHandler();

            /** Lancio il parsing, passandogli il file e l'handler che lo deve gestire */

            File f = new File( System.getProperty("user.dir"), Stringhe.FILE_DITTA_XML );
            saxParser.parse(f, handler);

            // Al suo interno il parser creerà una lista di Turno
            List<Turno> turni = handler.getTurni();

            // e lo restituisce al batch che chiama questa findAll()
            return turni;

        } catch (ParserConfigurationException | SAXException | IOException e) { // | URISyntaxException
            throw  new RuntimeException(e);
        }
    }
}
