package com.beltra.sistema2;

import com.beltra.sistema2.client.FileClientAutista;
import com.beltra.sistema2.client.FileClientAutobus;
import com.beltra.sistema2.client.FileClientDitta;
import com.beltra.sistema2.client.FileClientTurno;


import com.beltra.sistema2.model.dao.DAOFactory;
import com.beltra.sistema2.model.dao.RetribuzioneDAO;
import com.beltra.sistema2.model.dao.TurnoDAO;
import com.beltra.sistema2.model.dao.XMLImpl.TurnoDAOXMLImpl;

import com.beltra.sistema2.model.mo.Retribuzione;
import com.beltra.sistema2.model.mo.Turno;
import com.beltra.sistema2.service.AutistaService;
import com.beltra.sistema2.service.AutobusService;
import com.beltra.sistema2.service.TurnoService;
import com.beltra.sistema2.utils.Stringhe;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Startpoint dell'applicazione
 *
 */
public class Sistema2
{
    static AutistaService autistaService = new AutistaService();
    static AutobusService autobusService = new AutobusService();
    static TurnoService turnoService = new TurnoService();

    static List<Turno> listaTurni = new ArrayList<>();

    public static void main( String[] args ) throws InterruptedException {


        /** Recupero un file XML da Sistema1 */
        File f = new File(Stringhe.DIRECTORY_PROGETTO, Stringhe.FILE_DITTA_XML);
        if( !f.exists()) {
            System.out.println("\n*******************\nRECUPERO FILE XML\n*******************");
            recuperaXML();
        }




        /** Stampo in HTML i risultati che mi interessano, tramite XSL */
        System.out.println("\n*******************\nGENERAZIONE PROSPETTI IN HTML\n*******************");
        autistaService.generaHTMLAutisti();
        autobusService.generaHTMLBus();
        turnoService.generaHTMLTurni();
        turnoService.generaHTMLTurniByCodiceAutista("A002");


        System.out.println("\n*******************\nGESTIONE RETRIBUZIONI\n*******************");
        System.out.println("Inizio recupero lista turni: attendere prego");
        TurnoDAO turnoDAO = new TurnoDAOXMLImpl(DAOFactory.XMLIMPL);
        listaTurni = turnoDAO.findAll();
        System.out.println("Fine recupero lista turni\n");



        // TODO: a scopo di test chiamo la calcolaImportoRetribuzione() !!
        //RetribuzioneDAOMYSQLJDBCImpl retribuzioneDAOMYSQLJDBC = new RetribuzioneDAOMYSQLJDBCImpl();
        // double importoRetribuzione = retribuzioneDAOMYSQLJDBC.calcolaImportoRetribuzione(listaTurni);



        /** TODO: Gestione Retribuzioni: accesso al DB */
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Calcolo delle retribuzioni\n");

        DAOFactory mySQLDAOFactory = null;

        try {
            List<Retribuzione> retribuzioni;
            mySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQLJDBCIMPL);

            mySQLDAOFactory.beginTransaction();
            RetribuzioneDAO mySQLRetribuzioneDAO = mySQLDAOFactory.getRetribuzioneDAO();
            mySQLRetribuzioneDAO.creaRetribuzione(listaTurni);
            System.out.println("Retribuzioni scritte a DB");

            mySQLDAOFactory.commitTransaction();
            mySQLDAOFactory.closeTransaction();

        } catch (Throwable e) {
            e.printStackTrace(System.err);
            mySQLDAOFactory.rollbackTransaction();
        }





    } // fine main



    /** Recuperare il/i file XML da Sistema 1<br>
     *  Tali files vengono salvati nelle resources del progetto
     * */
    private static void recuperaXML() {
        String whichXML = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("\n****************************************\nSeleziona il file da ottenere dal Sistema1:\n['DITTA', 'AUTOBUS', 'AUTISTA', 'TURNO']\n'FINE' per terminare la ricezione di file\n****************************************\n");
            whichXML = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        whichXML = whichXML.toUpperCase();

        while( !(whichXML.equals("FINE")) ) {

        switch (whichXML) {
            case "DITTA":
                FileClientDitta fileClientDitta = new FileClientDitta();
                fileClientDitta.riceviXML();
                break;
            case "AUTOBUS":
                FileClientAutobus fileClientAutobus = new FileClientAutobus();
                fileClientAutobus.riceviXML();
                break;
            case "AUTISTA":
                FileClientAutista fileClientAutista = new FileClientAutista();
                fileClientAutista.riceviXML();
                break;
            case "TURNO":
                FileClientTurno fileClientTurno = new FileClientTurno();
                fileClientTurno.riceviXML();
                break;
            default:
                break;
            }

            System.out.println("Digita un qualsiasi tasto per continuare\n'FINE' per terminare la ricezione di file ");
            try {
                whichXML = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            whichXML = whichXML.toUpperCase();

        }
    }



}




