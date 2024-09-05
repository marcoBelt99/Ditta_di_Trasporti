package com.beltra.sistema2.model.dao.XMLImpl;

import com.beltra.sistema2.model.dao.AutistaDAO;
import com.beltra.sistema2.model.mo.Autista;
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


public class AutistaDAOXMLImpl implements AutistaDAO  {

    // URL xmlDirURL;
        String xmlDirURL;

    public AutistaDAOXMLImpl(String xmlDirURL) {
        this.xmlDirURL = xmlDirURL;

    }

    @Override
    public List<Autista> insertAutisti() {
       throw new UnsupportedOperationException("Operazione di inserimento non ancora supportata.");
    }


    @Override
    public List<Autista> findAll() {

        /** TODO */
        // Istanzio un parser e uno Schema Factory
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        /** Recupero il file XSD */
        File fileXSD = new File(Stringhe.DIRECTORY_PROGETTO, Stringhe.FILE_DITTA_XSD);

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();

            // TODO: richiamo file XSD per la validazione
            schemaFactory.newSchema(fileXSD);
            saxParserFactory.setValidating(true); // gli dico che devo fare la validazione (con xsd o con dtd. Nel mio caso xsd).
            saxParserFactory.setNamespaceAware(true); // Attivo il supporto ai namespace XML

            // Dico al parser che la validazione deve essere fatta rispetto all'XML Schema
            saxParser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema"
            );

            // Creo un handler
            AutistaDAOXMLHandler handler = new AutistaDAOXMLHandler();

            // Definisco il file di importazione
            // URL XMLImportFileURL = new URL(xmlDirURL.toString() + "/import/ditta.xml");

            // Lancio il parsing, passandogli il file e l'handler che lo deve gestire


            File f = new File( System.getProperty("user.dir"), Stringhe.FILE_DITTA_XML );
            // saxParser.parse(new File(XMLImportFileURL.toURI()), handler);
            saxParser.parse(f, handler);

            // Al suo interno il parser creerà una lista di Autista
            List<Autista> autisti = handler.getAutisti();

            // e lo restituisce al batch che chiama questa findAll()
            return autisti;

        } catch (ParserConfigurationException | SAXException | IOException  e) { // | URISyntaxException
            throw  new RuntimeException(e);
        }
    }
}
