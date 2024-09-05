package com.beltra.sistema2.model.dao;

import com.beltra.sistema2.model.dao.XMLImpl.XMLDAOFactory;
import com.beltra.sistema2.model.dao.mySQLJDBCImpl.MySQLJDBCDAOFactory;

import java.net.MalformedURLException;

public abstract class DAOFactory {
// Lista di DAO supportati dalla factory

public static final String MYSQLJDBCIMPL  = "MySQLJDBCImpl";
public static final String XMLIMPL = "XMLImpl";

public abstract void beginTransaction();

public abstract void commitTransaction();

public abstract void rollbackTransaction();

public abstract  void closeTransaction();

/** Getters dei DAO */
public abstract AutistaDAO getAutistaDAO();

public abstract AutobusDAO getAutobusDAO();

public abstract TurnoDAO getTurnoDAO();

public abstract RetribuzioneDAO getRetribuzioneDAO();

public static DAOFactory getDAOFactory(String whichFactory) {

    switch (whichFactory) {
        case MYSQLJDBCIMPL:
            return new MySQLJDBCDAOFactory();
        case XMLIMPL:
            return new XMLDAOFactory();
        default:
            return null;
    }


}

}
