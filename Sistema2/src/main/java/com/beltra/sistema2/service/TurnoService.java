package com.beltra.sistema2.service;

import com.beltra.sistema2.model.dao.AutobusDAO;
import com.beltra.sistema2.model.dao.DAOFactory;
import com.beltra.sistema2.model.dao.TurnoDAO;
import com.beltra.sistema2.model.mo.Autobus;
import com.beltra.sistema2.model.mo.Turno;
import com.beltra.sistema2.utils.Stringhe;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class TurnoService extends Service {

    public TurnoService() {

    }

    public static void recuperaEStampaTurni() {

        DAOFactory xmlDAOFactory = null;

        try {
            xmlDAOFactory = DAOFactory.getDAOFactory( DAOFactory.XMLIMPL );
            List<Turno> turni;

            // Lancio la beginTransaction() del factory XML per agganciare la caertella con le risorse
            xmlDAOFactory.beginTransaction();

            // Ottengo il TurnoDAO
            TurnoDAO xmlTurnoDAO = xmlDAOFactory.getTurnoDAO();

            // findAll(): leggo l'XML con SAX e creo la lista
            turni = xmlTurnoDAO.findAll();

            // Stampo le cose lette
            System.out.println("Turni letti dal file di input: " + turni.size());
//            turni.forEach(t -> System.out.println( t ));
            for (Turno turno : turni)
                System.out.println( turno );
            System.out.println("-----------------------------------------------");

            // Committo la transazione con la factory
            xmlDAOFactory.commitTransaction();

            // Chiudo la factory
            xmlDAOFactory.closeTransaction();

        } catch (Throwable e) {
            e.printStackTrace(System.err);
            xmlDAOFactory.rollbackTransaction();
        }

    }



    /** Ritorna la lista dei turni XML */
    public static List<Turno> getTurni() {

        DAOFactory xmlDAOFactory = null;
        List<Turno> turni = null;

        try {
            // Si becca la DAO Factory XML
            xmlDAOFactory = DAOFactory.getDAOFactory(DAOFactory.XMLIMPL);

            // Lancio la beginTransaction() del factory XML per agganciare la cartella con le risorse
            xmlDAOFactory.beginTransaction();

            // Ottengo il DAO del turno
            TurnoDAO xmlTurnoDAO = xmlDAOFactory.getTurnoDAO();

            // TODO:chiamo findAll(), leggo l'XML e creo la lista
            turni = xmlTurnoDAO.findAll();

            xmlDAOFactory.commitTransaction();
            xmlDAOFactory.closeTransaction();

            return turni;

        } catch (Throwable e) {
            e.printStackTrace(System.err);
            xmlDAOFactory.rollbackTransaction();
        }
        return turni;
    }


    public List<Turno> getTurniAutistaByCodice(String codiceAutista) {
        return getTurni()
                .stream()
                .filter( turno -> codiceAutista.equals(turno.getAutista().getCodice()) )// Prendi i soli turni dell'autista di codice: codiceAutista
                .collect(Collectors.toList());

    }

    public List<Turno> getTurniAutistaByCodiceFilterByMese(String codiceAutista, String mese) {
        return getTurniAutistaByCodice( codiceAutista )
                .stream()
                .filter( turno -> turno.getData().getMonth() == Stringhe.mesiMap.get(mese) ) // Prendi i soli turni che fanno parte d
                .collect(Collectors.toList());
    }


    /** Genera l'elenco dei turni */
    public void generaHTMLTurni() {
        super.generaHTML(Stringhe.FILE_TURNI_XSL, Stringhe.FILE_DITTA_XML, Stringhe.FILE_TURNI_HTML);
        System.out.println("Generato file HTML: " + Stringhe.FILE_TURNI_HTML);

    }


    public void generaHTMLTurniByCodiceAutista(String codice) {

        // Creazione di StreamSource per XSLT e XML
        StreamSource xslt = new StreamSource(new File(   Stringhe.FILE_TURNI_AUTISTI_BY_CODICE_AUTISTA_XSL  ));
        StreamSource xml = new StreamSource(new File(   Stringhe.FILE_DITTA_XML   ));
        StreamResult output = new StreamResult(new File( Stringhe.FILE_TURNI_AUTISTI_BY_CODICE_AUTISTA_HTML ));

        // Creazione del Transformer
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer(xslt);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }

        // TODO: Impostazione del parametro
        transformer.setParameter("codiceAutista", new String(codice));

        // Esegui la trasformazione
        try {
            transformer.transform(xml, output);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Generato file HTML: " + Stringhe.FILE_TURNI_AUTISTI_BY_CODICE_AUTISTA_XSL);
    }


}

