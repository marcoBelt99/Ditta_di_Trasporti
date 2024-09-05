package com.beltra.sistema1.systemintegration.si;


import com.beltra.sistema1.service.*;
import com.beltra.sistema1.systemintegration.elenchi.Turni;


import com.beltra.sistema1.utils.Stringhe;
import com.beltra.sistema1.utils.SystemIntegrationUtils;
import jakarta.xml.bind.JAXB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


@Component
public class SystemIntegrationTurni implements  SystemIntegration {

    @Autowired
    private TurniService turniService;

    @Autowired
    private UtentiService utentiService;

    @Autowired
    private LineeService lineeService;

    @Autowired
    private AutobusService autobusService;


    String pathRelativo = "src/main/resources/xml.export/turni.xml"; //

    File f = new File( Stringhe.DIRECTORYPROGETTO, pathRelativo );

    private Turni turni;

    SystemIntegrationUtils sysIntegrUtils;


    @Override
    public void produciXML() {

        // Necessario per richiamare la funzione di creazione della lista da esportare
        sysIntegrUtils = new SystemIntegrationUtils( utentiService, lineeService, autobusService, turniService  );

        // Creo un nuovo elenco di Turno che ha come root la lista
        turni = new Turni();

        turni.setListaTurni( sysIntegrUtils.generaListaTurniDaEsportare() );

        // StringWriter per stampare a video
        StringWriter sw = new StringWriter();

        // TODO: conversione da oggetto Java ad XML
        JAXB.marshal(turni, sw);

        // Visualizzo a video l'XML generato
        System.out.println(sw);


        JAXB.marshal( turni, new File(Stringhe.DIRECTORYPROGETTO, pathRelativo) );

    }


    /** Invio del file XML prodotto tramite una socket
     *  <br>
     *  PORTA: 8087
     * */
    public void inviaXML() {
        int porta = 8087;

        if( !(f.exists()) || (f.isDirectory()))
        {
            System.err.println( "Errore nell'accesso al file" );
            return ;
        }


        try ( ServerSocket s = new ServerSocket(porta) )
        {
            System.out.println( "Sistema 1 in ascolto sulla porta: " + porta);

            int cont = 0;
            while(cont != 1) {

                try (Socket ss = s.accept()) {
                    System.out.println("Connessione accettata: " + ss);

                    cont++;

                    // Flusso per inviare i dati:
                    FileInputStream fis = new FileInputStream( f.getAbsolutePath() ); // Stringhe.DIRECTORYPROGETTO+pathRelativo
                    OutputStream outputStream = ss.getOutputStream();

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    // Leggi i dati dal file e inviali al client
                    while ((bytesRead = fis.read(buffer)) != -1)
                        outputStream.write(buffer, 0, bytesRead);

                    System.out.println("File inviato con successo!");

                } catch (IOException e) {
                    System.err.println("Errore nell'invio del file, " + e.getMessage());
                    s.close();
                }
            }


        }
        catch (IOException e) {
            System.err.println( "Errore di Sistema1: " + e.getMessage() );
        }
    }


}
