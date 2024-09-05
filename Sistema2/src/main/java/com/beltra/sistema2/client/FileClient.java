package com.beltra.sistema2.client;

import com.beltra.sistema2.utils.Stringhe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public abstract class FileClient {

    FileClient() {

    }


    /** Apre una socket verso una determianta porta per ricevere uno specifico file XML */
    public void riceviXML(String pathRelativo, File f, int porta) {

        try (Socket s = new Socket( Stringhe.INDIRIZZO_IP_SERVER_SISTEMA1, porta )) {
            System.out.println("Connessione al server " + Stringhe.INDIRIZZO_IP_SERVER_SISTEMA1 + " sulla porta " + porta);

            // TODO: Flusso per ricevere i dati
            try (InputStream inputStream = s.getInputStream();
                 FileOutputStream fileOutputStream = new FileOutputStream( pathRelativo )) {

                byte[] buffer = new byte[4096];
                int bytesRead;

                // TODO: Legge i dati dal server e li scrive nel file
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File ricevuto e salvato con successo.");
            } catch (IOException e) {
                System.err.println("Errore nella ricezione del file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Errore del client: " + e.getMessage());
        }
    }
}
