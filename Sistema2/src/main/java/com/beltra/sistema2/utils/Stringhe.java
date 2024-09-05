package com.beltra.sistema2.utils;

import java.util.Map;

/** Questa classe offre i principali alias per riferirsi ai vari percorsi dei file */
public class Stringhe {

    // TODO: Indirizzo IP del server (necessario per il collegamento con le socket)
    public static String INDIRIZZO_IP_SERVER_SISTEMA1 = "localhost";


    public static String DIRECTORY_PROGETTO = System.getProperty("user.dir");
    public static String FILE_DITTA_XSD = "src/main/resources/xml.import/XSD/ditta.xsd";
    public static String FILE_DITTA_XML = "src/main/resources/xml.import/ditta.xml";


    public static String FILE_TURNI_XSL = "src/main/resources/xml.import/XSL/elencoTurni.xsl";
    public static String FILE_TURNI_HTML = "src/main/resources/xml.import/HTML/elencoTurni.html";
    public static String FILE_TURNI_AUTISTI_BY_CODICE_AUTISTA_XSL = "src/main/resources/xml.import/XSL/elencoTurniByCodiceAutista.xsl";
    public static String FILE_TURNI_AUTISTI_BY_CODICE_AUTISTA_HTML = "src/main/resources/xml.import/HTML/elencoTurniByCodiceAutista.html";



    public static String FILE_AUTISTI_XSL = "src/main/resources/xml.import/XSL/elencoAutisti.xsl";
    public static String FILE_AUTISTI_HTML = "src/main/resources/xml.import/HTML/elencoAutisti.html";



    public static String FILE_BUS_XSL = "src/main/resources/xml.import/XSL/elencoAutobus.xsl";
    public static String FILE_BUS_HTML = "src/main/resources/xml.import/HTML/elencoAutobus.html";






    public static enum MESI

    { GENNAIO, FEBBRAIO, MARZO, APRILE, MAGGIO, GIUGNO, LUGLIO, AGOSTO, SETTEMBRE, OTTOBRE, NOVEMBRE, DICEMBRE} ;


    public static final Map<String, Integer> mesiMap = Map.ofEntries(
            Map.entry("gennaio",    MESI.GENNAIO.ordinal() ),
            Map.entry("febbraio",   MESI.FEBBRAIO.ordinal() ),
            Map.entry("marzo",      MESI.MARZO.ordinal() ),
            Map.entry("aprile",     MESI.APRILE.ordinal() ),
            Map.entry("maggio",     MESI.MAGGIO.ordinal() ),
            Map.entry("giugno",     MESI.GIUGNO.ordinal() ),
            Map.entry("luglio",     MESI.LUGLIO.ordinal() ),
            Map.entry("agosto",     MESI.AGOSTO.ordinal() ),
            Map.entry("settembre",  MESI.SETTEMBRE.ordinal() ),
            Map.entry("ottobre",    MESI.OTTOBRE.ordinal() ),
            Map.entry("novembre",   MESI.NOVEMBRE.ordinal() ),
            Map.entry("dicembre",   MESI.DICEMBRE.ordinal() )
    );


    public static final Map<Integer, String> mesiMapInversa = Map.ofEntries(
            Map.entry(  MESI.GENNAIO.ordinal() ,    "gennaio"),
            Map.entry(  MESI.FEBBRAIO.ordinal() ,   "febbraio"),
            Map.entry(  MESI.MARZO.ordinal() ,      "marzo"),
            Map.entry(  MESI.APRILE.ordinal() ,     "aprile"),
            Map.entry(  MESI.MAGGIO.ordinal() ,     "maggio"),
            Map.entry(  MESI.GIUGNO.ordinal() ,     "giugno"),
            Map.entry(  MESI.LUGLIO.ordinal() ,     "luglio"),
            Map.entry(  MESI.AGOSTO.ordinal() ,     "agosto"),
            Map.entry(  MESI.SETTEMBRE.ordinal() ,  "settembre"),
            Map.entry(  MESI.OTTOBRE.ordinal() ,    "ottobre"),
            Map.entry(  MESI.NOVEMBRE.ordinal() ,   "novembre"),
            Map.entry(  MESI.DICEMBRE.ordinal() ,   "dicembre")
    );


}