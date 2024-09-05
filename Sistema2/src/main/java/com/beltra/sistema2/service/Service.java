package com.beltra.sistema2.service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/** Offre i principali metodi per lavorare con i MO */
public abstract class Service {


    /** Effettua una trasformazione XSL da XML ad HTML per rappresentare l'elenco di MO di interesse */
    void generaHTML(String xsltPath, String xmlPath, String outputPath ) {

        // Creazione di StreamSource per XSLT e XML
        StreamSource xslt = new StreamSource(new File(   xsltPath  ));
        StreamSource xml = new StreamSource(new File(   xmlPath   ));
        StreamResult output = new StreamResult(new File( outputPath ));

        // Creazione del Transformer
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer(xslt);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }

        // Esegui la trasformazione
        try {
            transformer.transform(xml, output);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }
}


