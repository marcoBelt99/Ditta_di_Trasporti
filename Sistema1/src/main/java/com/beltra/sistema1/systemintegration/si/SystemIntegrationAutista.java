package com.beltra.sistema1.systemintegration.si;

import com.beltra.sistema1.domain.AutistiEntity;

import com.beltra.sistema1.service.UtentiService;

import com.beltra.sistema1.systemintegration.elenchi.Autisti;
import com.beltra.sistema1.systemintegration.mo.Autista;
import com.beltra.sistema1.utils.Stringhe;
import com.beltra.sistema1.utils.SystemIntegrationUtils;
import jakarta.xml.bind.JAXB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

@Component
public class SystemIntegrationAutista implements  SystemIntegration{

    @Autowired
    private UtentiService utentiService;


    String pathRelativo = "src/main/resources/xml.export/autisti.xml"; //

    File f = new File( Stringhe.DIRECTORYPROGETTO, pathRelativo );

    private Autisti autisti;

    SystemIntegrationUtils sysIntegrUtils;


    @Override
    public void produciXML() {

        // Necessario per richiamare la funzione di creazione della lista da esportare
        sysIntegrUtils = new SystemIntegrationUtils( utentiService );

        // Creo un nuovo elenco di Autisti che ha come root la lista
        autisti = new Autisti();

        autisti.setListaAutisti( sysIntegrUtils.generaListaAutistiDaEsportare() );


        // StringWriter per stampare a video
        StringWriter sw = new StringWriter();

        // TODO: conversione da oggetto Java ad XML

        JAXB.marshal(autisti, sw);

        // Visualizzo a video l'XML generato
        System.out.println(sw);

        JAXB.marshal( autisti, new File(Stringhe.DIRECTORYPROGETTO, pathRelativo) );

    }


    /** Invio del file XML prodotto tramite una socket
     *  <br>
     *  PORTA: 8086
     * */
    public void inviaXML() {
        int porta = 8086;

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

                    // TODO: Flusso per inviare i dati:
                    FileInputStream fis = new FileInputStream( f.getAbsolutePath() ); // Stringhe.DIRECTORYPROGETTO+pathRelativo
                    OutputStream outputStream = ss.getOutputStream();

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    // TODO: Leggi i dati dal file e inviali al client
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