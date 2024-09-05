package com.beltra.sistema2.model.dao.XMLImpl;

import com.beltra.sistema2.model.dao.*;

import java.net.MalformedURLException;
import java.net.URL;

public class XMLDAOFactory extends DAOFactory {

    // URL xmlDirURL;

    String xmlDir;

    @Override
    public void beginTransaction() {

        //xmlDirURL = ClassLoader.getSystemClassLoader().getResource("xml.import"); // problematico

        xmlDir = "";
        String dir = System.getProperty("user.dir");
        String pathRelativo = "src/main/resources/xml.import/ditta.xml";

        //System.out.println(dir.concat(pathRelativo)); // scopo di debug

        xmlDir = dir.concat(pathRelativo);

        if(xmlDir == null) throw new RuntimeException("Impossibile Trovare Cartella XML");
    }

    @Override
    public void commitTransaction() {

    }

    @Override
    public void rollbackTransaction() {

    }

    @Override
    public void closeTransaction() {

    }

    @Override
    public AutistaDAO getAutistaDAO() {
        return new AutistaDAOXMLImpl(xmlDir);
    }

    @Override
    public AutobusDAO getAutobusDAO() {
        return new AutobusDAOXMLImpl(xmlDir);
    }

    @Override
    public TurnoDAO getTurnoDAO() {
        return new TurnoDAOXMLImpl(xmlDir);
    }

    @Override
    public RetribuzioneDAO getRetribuzioneDAO() {
        return null;
    }
}
