package com.beltra.sistema2.client;

import java.io.File;


public class FileClientAutobus extends FileClient {

    private String pathRelativo;
    private File f;


    public FileClientAutobus() {
        this.pathRelativo = "src/main/resources/xml.import/autobus.xml";
        this.f = new File( System.getProperty("user.dir"), pathRelativo );
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
        super.riceviXML(getPathRelativo(), getF(), 8085);
    }
}
