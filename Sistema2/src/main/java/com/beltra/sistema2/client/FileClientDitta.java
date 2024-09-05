package com.beltra.sistema2.client;

import com.beltra.sistema2.utils.Stringhe;

import java.io.*;

public class FileClientDitta extends  FileClient {

    private String pathRelativo;
    private File f;


    public FileClientDitta() {
        this.pathRelativo = "src/main/resources/xml.import/ditta.xml";
        //this.f = new File( System.getProperty("user.dir"), pathRelativo );
        this.f = new File(Stringhe.DIRECTORY_PROGETTO, Stringhe.FILE_DITTA_XML);
    }


    public String getPathRelativo() {
        return pathRelativo;
    }

    public void setPathRelativo(String pathRelativo) {
        this.pathRelativo = pathRelativo;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public void riceviXML() {
        super.riceviXML(getPathRelativo(), getF(), 8084);
    }
}
