package com.beltra.sistema2.service;

import com.beltra.sistema2.model.dao.AutistaDAO;
import com.beltra.sistema2.model.dao.AutobusDAO;
import com.beltra.sistema2.model.dao.DAOFactory;
import com.beltra.sistema2.model.mo.Autista;
import com.beltra.sistema2.model.mo.Autobus;
import com.beltra.sistema2.utils.Stringhe;

import java.util.Arrays;
import java.util.List;

public class AutobusService extends Service{

    public static void recuperaEStampaBus() {

        DAOFactory xmlDAOFactory = null;

        try {
            xmlDAOFactory = DAOFactory.getDAOFactory( DAOFactory.XMLIMPL );
            List<Autobus> autobusList;

            // Lancio la beginTransaction() del factory XML per agganciare la caertella con le risorse
            xmlDAOFactory.beginTransaction();

            // Ottengo l'AutobusDAO
            AutobusDAO xmlAutobusDAO = xmlDAOFactory.getAutobusDAO();

            // findAll(): leggo l'XML con SAX e creo la lista
            autobusList = xmlAutobusDAO.findAll();

            // Stampo le cose lette
            System.out.println("Autobus letti dal file di input: " + autobusList.size());
//            autobusList.forEach(autobus -> System.out.println(autobus));
            for (Autobus autobus : autobusList)
                System.out.println( autobus );
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


    /** Ritorna la lista degli autobus tramite XML */
    public static List<Autobus> getAutobusList() {

        DAOFactory xmlDAOFactory = null;
        List<Autobus> autobusList = null;

        try {
            // Si becca la DAO Factory XML
            xmlDAOFactory = DAOFactory.getDAOFactory(DAOFactory.XMLIMPL);

            // Lancio la beginTransaction() del factory XML per agganciare la cartella con le risorse
            xmlDAOFactory.beginTransaction();

            // Ottengo l'AutistaDAO
            AutobusDAO xmlAutobusDAO = xmlDAOFactory.getAutobusDAO();

            // TODO:chiamo findAll(), leggo l'XML e creo la lista
            autobusList = xmlAutobusDAO.findAll();

            xmlDAOFactory.commitTransaction();
            xmlDAOFactory.closeTransaction();


            return autobusList;


        } catch (Throwable e) {
            e.printStackTrace(System.err);
            xmlDAOFactory.rollbackTransaction();
        }
        return autobusList;
    }

    /** Genera l'elenco degli autobus */
    public void generaHTMLBus() {
        super.generaHTML(Stringhe.FILE_BUS_XSL, Stringhe.FILE_DITTA_XML, Stringhe.FILE_BUS_HTML);
        System.out.println("Generato file HTML: " + Stringhe.FILE_BUS_HTML);

    }

}
