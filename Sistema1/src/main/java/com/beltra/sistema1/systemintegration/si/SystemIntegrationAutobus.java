package com.beltra.sistema1.systemintegration.si;


import com.beltra.sistema1.service.AutobusService;
import com.beltra.sistema1.systemintegration.elenchi.Bus;

import com.beltra.sistema1.utils.Stringhe;
import com.beltra.sistema1.utils.SystemIntegrationUtils;
import jakarta.xml.bind.JAXB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


@Component
public class SystemIntegrationAutobus implements SystemIntegration {

    @Autowired
    AutobusService autobusService;

    String pathRelativo = "src/main/resources/xml.export/bus.xml"; //

    File f = new File( Stringhe.DIRECTORYPROGETTO, pathRelativo );

    /** TODO: bus è inteso come un elenco di oggetti di tipo Autobus */
    private Bus bus;

    SystemIntegrationUtils sysIntegrUtils;


    @Override
    public void produciXML() {
        // Necessario per richiamare la funzione di creazione della lista da esportare
        sysIntegrUtils = new SystemIntegrationUtils( autobusService );

        // Genero una lista di elementi di tipo <Autobus>
        bus = new Bus();

        bus.setListaAutobus( sysIntegrUtils.generaListaAutobusDaEsportare() );


        // StringWriter per stampare a video
        StringWriter sw = new StringWriter();

        // TODO: conversione da oggetto Java ad XML
        JAXB.marshal(bus, sw);

        // Visualizzo a video l'XML generato
        System.out.println(sw);

        JAXB.marshal( bus, new File(Stringhe.DIRECTORYPROGETTO, pathRelativo) );


    }


    /** Invio del file XML prodotto tramite una socket
     *  <br>
     *  PORTA: 8085
     * */
    public void inviaXML() {

        int porta = 8085;


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
                }
            }


        }
        catch (IOException e) {
            System.err.println( "Errore di Sistema1: " + e.getMessage() );
        }

    }
}
