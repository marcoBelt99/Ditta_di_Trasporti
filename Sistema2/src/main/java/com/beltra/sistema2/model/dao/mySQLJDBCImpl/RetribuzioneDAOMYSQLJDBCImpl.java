package com.beltra.sistema2.model.dao.mySQLJDBCImpl;

import com.beltra.sistema2.model.dao.RetribuzioneDAO;
import com.beltra.sistema2.model.mo.Autista;
import com.beltra.sistema2.model.mo.Retribuzione;
import com.beltra.sistema2.model.mo.Turno;
import com.beltra.sistema2.service.AutistaService;
import com.beltra.sistema2.utils.Stringhe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class RetribuzioneDAOMYSQLJDBCImpl implements RetribuzioneDAO {

    Connection conn;

    public RetribuzioneDAOMYSQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void creaRetribuzione(List<Turno> turni) {

        List<Retribuzione> retribuzioni = null;
        try {
            retribuzioni = calcolaImportoRetribuzione(turni);
        } catch (InterruptedException e) {
            System.err.println("Errore in fase di creazione della lista delle retribuzioni");
            throw new RuntimeException(e);
        }

        PreparedStatement ps;

        Integer idRetribuzione;

        try {

            String sql = "INSERT INTO retribuzione (id, importo, mese, codice_autista)" +
                    " VALUES (?, ?, ?, ?);";

            ps = conn.prepareStatement(sql);

            System.out.println("Lista di retribuzioni:");
            retribuzioni.forEach( r -> System.out.println( r ) );

            for(Retribuzione r : retribuzioni) {
                ps.setInt(1, r.getId());
                ps.setDouble( 2, r.getImporto()  );
                ps.setString( 3, r.getMese() );
                ps.setString( 4, r.getAutista().getCodice() );
                ps.addBatch();
            }

            ps.executeBatch();
            ps.close();

        } catch (SQLException e) {
            System.err.println("Errore query SQL ");
            throw new RuntimeException(e);
        }

    }




    @Override
    public List<Retribuzione> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    /** TODO: Gestione retribuzioni */
    public List<Retribuzione> calcolaImportoRetribuzione(List<Turno> listaTurni) throws InterruptedException {

        AutistaService autistaService = new AutistaService();

        List<Retribuzione> retribuzioni = new ArrayList<>();


        /** Mi genero una lista di codici di tutti gli autisti */
        List<String> listaCodiciAutista = new ArrayList<>();
        for (Autista a : autistaService.getAutistiFromXML() )
            listaCodiciAutista.add( a.getCodice() );


        // *************************** TODO: TEST ***************************

        /**
        // Ho suddiviso i turni per mesi
        Map< String, List<Turno>> elencoTurniAutista2Map = suddividiTurniPerMese( "A002", listaTurni );
        // Stampa i turni per mese TODO: attento che questo è un forEach applicato alla mappa!!
        elencoTurniAutista2Map.forEach( (mese, turni) -> {
            System.out.println(mese + ": " + turni + "\n");
        });

        //  Ottengo i turni dell'autista 2 di maggio
        //        SELECT * FROM public.turni WHERE EXTRACT(MONTH FROM data) = 5 and id_utente = 2;
        System.out.println("Ottengo i turni dell'autista 2 di maggio:\n");
        elencoTurniAutista2Map.get( "maggio" ).forEach(t -> System.out.println(t));
        */

        // ************************* TODO: implementazione ***************************

        double importo = 30.0;
        double totaleMese = 0.0;

        int numTurni = 0;

        double sommaImporto = 0.0d;
        long durataInMinuti = 0;
        double durataInOre = 0.0d;


        int capienza = 0;

        AtomicInteger idRetribuzione = new AtomicInteger(0);

        Map<String, List<Turno>> elencoTurniAutistaMap;
        for ( String codiceAutista : listaCodiciAutista )
        {


             importo = 0.0;
             sommaImporto = 0.0;
             totaleMese = 0.0;

             numTurni = 0;
             durataInMinuti = 0;
             durataInOre = 0.0;

            for (String nomeMese : Stringhe.mesiMap.keySet())
            {

                elencoTurniAutistaMap = suddividiTurniPerMese(codiceAutista, listaTurni);
                double importoBase = 30.0;
                double importoMese = 0.0;



                /** importo = importo * numero_turni_di_questo_mese */
                numTurni = elencoTurniAutistaMap.get(nomeMese).size();

                importo = numTurni < 1 ? 30.0 : (importo * numTurni);


                for(Turno t : elencoTurniAutistaMap.get(nomeMese) )
                {

                    /** Calcolo della durata del singolo turno e aggiornamento dell'importo di conseguenza */
                    LocalTime from = t.getOraInizio().toLocalTime();
                    LocalTime to = t.getOraFine().toLocalTime();
                    Duration durata = Duration.between(from, to);

                    durataInMinuti = durata.toMinutes();
                    durataInOre = durataInMinuti / 60;


                    if (durataInOre > 0 && durataInOre <= 4)
                        importoMese += (importoBase*0.7);
                    else if (durataInOre > 4 && durataInOre <= 6)
                        importoMese += (importoBase *0.8);
                    else if (durataInOre > 6 && durataInOre <= 8)
                        importoMese += (importoBase*0.9);

                    /**  Calcolo della capienza e aggiornamento dell'importo di conseguenza */
                    capienza = t.getAutobus().getCapienza();

                    if( capienza > 1 && capienza <= 12 )
                        importoMese += (importoBase * 0.95);
                    else if ( capienza > 12 && capienza <= 40 )
                        importoMese += (importoBase*0.97);
                    else if (capienza > 40 && capienza <= 60 )
                        importoMese += (importoBase*0.99);

                    //sommaImporto = sommaImporto + importo;
                    importoMese += importoBase;

                }
                totaleMese = sommaImporto;
                sommaImporto = 0.0;

                // Arrivato qui ho calcolato la retribuzione di questo mese per questo autista
                Retribuzione retribuzione =
                        new Retribuzione(
                                nomeMese,
                                autistaService
                                        .getAutistiFromXML()
                                        .stream()
                                        .filter(autista -> codiceAutista.equals(autista.getCodice()) )
                                        .collect(Collectors.toList())
                                        .getFirst()
                        );

                // Setto l'importo e l'id
                retribuzione.setImporto( Math.round (importoMese * 100.0) / 100.0 );
                retribuzione.setId( idRetribuzione.incrementAndGet() );


                // Quindi posso aggiungerla alla lista delle retribuzioni
                retribuzioni.add(  retribuzione );

            }

            importo = 0.0;
            sommaImporto = 0.0;
            totaleMese = 0.0;

            numTurni = 0;

            durataInMinuti = 0;
            durataInOre = 0.0;
            // passa al prossimo autista
    }

        return retribuzioni;

}



    public static Map<String, List<Turno>> suddividiTurniPerMese( String codiceAutista, List<Turno> listaTurni ) {

        /**  Mappa per memorizzare i turni per mese */
        Map<String, List<Turno>> turniPerMese = new LinkedHashMap<>();

        // Inizializzo la mappa con i nomi dei mesi
        for (String mese : Stringhe.mesiMap.keySet()) {
            if (!mese.isEmpty())
                turniPerMese.put(mese, new ArrayList<>());
        }

        // Filtro i turni per autista specifico e li aggiunge alla mappa
        for (Turno turno : listaTurni) {
            if (turno.getAutista().getCodice().equals(codiceAutista))
                turniPerMese.get( Stringhe.mesiMapInversa.get(turno.getData().getMonth())  ).add(turno);
        }

        // Ritorna la mappa con i turni suddivisi per mese
        return turniPerMese;
    }



}
