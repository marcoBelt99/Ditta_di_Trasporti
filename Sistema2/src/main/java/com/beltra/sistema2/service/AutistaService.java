package com.beltra.sistema2.service;

import com.beltra.sistema2.model.dao.AutistaDAO;
import com.beltra.sistema2.model.dao.DAOFactory;
import com.beltra.sistema2.model.mo.Autista;
import com.beltra.sistema2.utils.Stringhe;

import java.util.List;

public class AutistaService extends Service {

    public static void recuperaEStampaAutisti() {

        DAOFactory xmlDAOFactory = null;

        try {
            // Si becca la DAO Factory XML
            xmlDAOFactory = DAOFactory.getDAOFactory(DAOFactory.XMLIMPL);

            List<Autista> autisti;

            // Lancio la beginTransaction() del factory XML per agganciare la cartella con le risorse
            xmlDAOFactory.beginTransaction();

            // Ottengo l'AutistaDAO
            AutistaDAO xmlAutistaDAO = xmlDAOFactory.getAutistaDAO();

            // TODO:chiamo findAll(), leggo l'XML e creo la lista
            autisti = xmlAutistaDAO.findAll();

            // Stampa delle cose lette
            System.out.println("Autisti letti dal file di input: " + autisti.size() );
            for (Autista aut : autisti)
                System.out.println(aut);
            System.out.println("-----------------------------------------------");

            // Committo le factory (anche quella che farò per il DB)
            xmlDAOFactory.commitTransaction();

            // Chiudo le factory
            xmlDAOFactory.closeTransaction();


        } catch (Throwable e) {
            e.printStackTrace(System.err);
            xmlDAOFactory.rollbackTransaction();

        }

    }


    /** Ritorna la lista degli autisti tramite XML */
    public static List<Autista> getAutistiFromXML() {

        DAOFactory xmlDAOFactory = null;

        List<Autista> autisti;
        try {
            // Si becca la DAO Factory XML
            xmlDAOFactory = DAOFactory.getDAOFactory(DAOFactory.XMLIMPL);

            // Lancio la beginTransaction() del factory XML per agganciare la cartella con le risorse
            xmlDAOFactory.beginTransaction();

            // Ottengo l'AutistaDAO
            AutistaDAO xmlAutistaDAO = xmlDAOFactory.getAutistaDAO();

            // TODO:chiamo findAll(), leggo l'XML e creo la lista
            autisti = xmlAutistaDAO.findAll();

            xmlDAOFactory.commitTransaction();
            xmlDAOFactory.closeTransaction();


            return autisti;


        } catch (Throwable e) {
            e.printStackTrace(System.err);
            xmlDAOFactory.rollbackTransaction();
        }
       return null;
    }

    /** Genera l'elenco dei autisti */
    public void generaHTMLAutisti() {
        super.generaHTML(Stringhe.FILE_AUTISTI_XSL, Stringhe.FILE_DITTA_XML, Stringhe.FILE_AUTISTI_HTML);
        System.out.println("Generato file HTML: " + Stringhe.FILE_AUTISTI_HTML);
    }


}
