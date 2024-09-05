package com.beltra.sistema1.utils;

import java.util.Map;

/** Classe contenente le principali stringhe che possono essere usate in più parti dell'applicazione. */
public class Stringhe {
    public static final String AUTISTA = "AUTISTA";
    public static final String DIRECTORYPROGETTO = System.getProperty("user.dir");


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

     /*

    public static final Map<String, String> MESI = Map.ofEntries(
            Map.entry("gennaio", "01"),
            Map.entry("febbraio", "02"),
            Map.entry("marzo", "03"),
            Map.entry("aprile", "04"),
            Map.entry("maggio", "05"),
            Map.entry("giugno", "07"),
            Map.entry("luglio", "07"),
            Map.entry("agosto", "08"),
            Map.entry("settembre", "09"),
            Map.entry("ottobre", "10"),
            Map.entry("novembre", "11"),
            Map.entry("dicembre", "12")
    );

         */


}
