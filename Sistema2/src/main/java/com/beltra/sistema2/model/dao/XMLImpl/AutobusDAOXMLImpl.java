package com.beltra.sistema2.model.dao.XMLImpl;

import com.beltra.sistema2.model.dao.AutobusDAO;
import com.beltra.sistema2.model.mo.Autobus;
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

public class AutobusDAOXMLImpl implements AutobusDAO {

    String xmlDirURL;

    public AutobusDAOXMLImpl(String xmlDirURL) {
        this.xmlDirURL = xmlDirURL;
    }

    @Override
    public List<Autobus> insertAutoubus() {
        throw new UnsupportedOperationException("Operazione di inserimento non ancora supportata.");
    }

    @Override
    public List<Autobus> findAll() {
        /** Istanzio un parser e uno Schema Factory */
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        /** Recupero il file XSD */
        File fileXSD = new File(Stringhe.DIRECTORY_PROGETTO, Stringhe.FILE_DITTA_XSD);

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();

            /** Imposto il file XSD per la validazione, e abilito sia la validazione che il supporto ai namespace */
            schemaFactory.newSchema(fileXSD);
            saxParserFactory.setValidating(true);
            saxParserFactory.setNamespaceAware(true);

            saxParser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema"
            );

            // Creo un handler
            AutobusDAOXMLHandler handler = new AutobusDAOXMLHandler();

            /** Definisco il file di importazione XML */
            File fileXML = new File( Stringhe.DIRECTORY_PROGETTO, Stringhe.FILE_DITTA_XML );

            /** Lancio il parsing */
            saxParser.parse( fileXML, handler );

            /** Al suo interno il parser creerà una lista di Autobus, che salvo
             *  e restituisco */
            List<Autobus> autobusList = handler.getAutobusList();

            return autobusList;

        } catch ( ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException( e );
        }
    }
}
