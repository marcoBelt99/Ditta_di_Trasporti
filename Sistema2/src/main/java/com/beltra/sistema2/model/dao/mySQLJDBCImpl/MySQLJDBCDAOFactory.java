package com.beltra.sistema2.model.dao.mySQLJDBCImpl;

import com.beltra.sistema2.model.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

public class MySQLJDBCDAOFactory extends DAOFactory {

    public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String SERVER_TIMEZONE = Calendar.getInstance().getTimeZone().getID();
    public static final String DATABASE_NAME = "sistema2db";
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PASSWORD = "123stella";

    // public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/"+DATABASE_NAME;
    public static final String DATABASE_URL_LONG = "jdbc:mysql://127.0.0.1:3306/sistema2db?user=root&password=password&serverTimezone="+SERVER_TIMEZONE;


    private Connection connection;

    @Override
    public void beginTransaction() {
        try {
            Class.forName(DATABASE_DRIVER);
            // this.connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            this.connection = DriverManager.getConnection(DATABASE_URL_LONG);

            this.connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commitTransaction() {
        try {
            this.connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void rollbackTransaction() {
        try {
            this.connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void closeTransaction() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public AutistaDAO getAutistaDAO() {
        return null;
    }

    @Override
    public AutobusDAO getAutobusDAO() {
        return null;
    }


    @Override
    public TurnoDAO getTurnoDAO() {
        return null;
    }


    @Override
    public RetribuzioneDAO getRetribuzioneDAO() {
        return new RetribuzioneDAOMYSQLJDBCImpl(connection);
    }
}
