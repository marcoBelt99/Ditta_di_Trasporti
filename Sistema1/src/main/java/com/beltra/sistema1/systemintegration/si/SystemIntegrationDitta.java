package com.beltra.sistema1.systemintegration.si;

import com.beltra.sistema1.service.*;
import com.beltra.sistema1.systemintegration.mo.*;
import com.beltra.sistema1.utils.Stringhe;
import com.beltra.sistema1.utils.SystemIntegrationUtils;
import jakarta.xml.bind.JAXB;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;


/** Classe necessaria per creare i file XML da scambiare ad un altro sistema */

@Component
public class SystemIntegrationDitta implements  SystemIntegration {

    @Autowired
    private UtentiService utentiService;

    @Autowired
    private LineeService lineeService;

    @Autowired
    private AutobusService autobusService;

    @Autowired
    private TurniService turniService;

    SystemIntegrationUtils sysIntegrUtils;

    String pathRelativo = "src/main/resources/xml.export/ditta.xml"; //

    File f = new File( Stringhe.DIRECTORYPROGETTO, pathRelativo );

    @Override
    public void produciXML() {

        Provincia provincia = new Provincia();
        provincia.setCodice("FE");

        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia("Via Santa Maddalena 14");
        indirizzo.setCap("42121");
        indirizzo.setCitta("Ferrara");
        indirizzo.setProvincia( provincia );


        Ditta ditta = new Ditta();
        ditta.setNome("Pilotto Viaggi");

        ditta.setIndirizzo( indirizzo );

        ditta.setDescrizione("Da oltre 30 anni leader del trasporto privato su gomma.\n" +
                "           Offriamo il miglior servizio di trasporto attraverso la conoscenza del cliente,\n" +
                "           e investiamo sui migliori mezzi.\n" +
                "           Il servizio è esteso anche al basso veneto, e arriva fino a Padova!");


        sysIntegrUtils = new SystemIntegrationUtils(this.utentiService, this.lineeService, this.autobusService, this.turniService);

        ditta.setAutisti( sysIntegrUtils.generaListaAutistiDaEsportare() );
        ditta.setLinee( sysIntegrUtils.generaListaLineeDaEsportare() );
        ditta.setBus( sysIntegrUtils.generaListaAutobusDaEsportare() );
        ditta.setTurni( sysIntegrUtils.generaListaTurniDaEsportare()  );


        // StringWriter per stampare a video
        // StringWriter sw = new StringWriter();

        // TODO: conversione da oggetto Java ad XML
        // JAXB.marshal(ditta, sw);
        // System.out.println( sw.toString() );

        JAXB.marshal( ditta, new File(Stringhe.DIRECTORYPROGETTO, pathRelativo) );

    }

    /** Invio del file XML prodotto tramite una socket
     *  <br>
     *  PORTA: 8084
     * */
    public void inviaXML() {

        int porta = 8084;


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
