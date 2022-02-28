package com.example.demo2;

import java.io.IOException;
import java.sql.*;

public class BazaDanych {
    public static void zaladujDaneDoBazy(String nazwaBazy, int cenaSprzadazy, int cenaKupna) throws SQLException {
        System.out.println("Loading driver...");


        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11470672";
        String username = "sql11470672";
        String password = "ySC7iFZf6P";

        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");


        Statement stmt = connection.createStatement();

        DatabaseMetaData md;
        md = connection.getMetaData();
        System.out.println(
                md.getDatabaseProductName() + "\n" +
                        md.getDatabaseProductVersion() + "\n" +
                        md.getDriverName() + "\n" +
                        md.getURL() + "\n" +
                        md.getUserName() + "\n" +
                        md.supportsAlterTableWithAddColumn() + "\n" +
                        md.supportsAlterTableWithDropColumn() + "\n" +
                        md.supportsANSI92FullSQL() + "\n" +
                        md.supportsBatchUpdates() + "\n" +
                        md.supportsMixedCaseIdentifiers() + "\n" +
                        md.supportsMultipleTransactions() + "\n" +
                        md.supportsPositionedDelete() + "\n" +
                        md.supportsPositionedUpdate() + "\n" +
                        md.supportsSchemasInDataManipulation() + "\n" +
                        md.supportsTransactions() + "\n" +
                        md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE) + "\n" +
                        md.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE) + "\n" +
                        md.insertsAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE) + "\n" +
                        md.updatesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
        stmt.executeUpdate("CREATE TABLE Tibia_Coins (" + "id INT(64) NOT NULL AUTO_INCREMENT," + "czas DATETIME," + "cena_sell INT," + "cena_buy INT," + "PRIMARY KEY(id))");


    }

    public static void sprawdzBazeDanych() throws SQLException {
        System.out.println("Loading driver...");


        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11470672";
        String username = "sql11470672";
        String password = "ySC7iFZf6P";

        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");


        Statement stmt = connection.createStatement();

        DatabaseMetaData md;
        md = connection.getMetaData();
        System.out.println(
                md.getDatabaseProductName() + "\n" +
                        md.getDatabaseProductVersion() + "\n" +
                        md.getDriverName() + "\n" +
                        md.getURL() + "\n" +
                        md.getUserName() + "\n" +
                        md.supportsAlterTableWithAddColumn() + "\n" +
                        md.supportsAlterTableWithDropColumn() + "\n" +
                        md.supportsANSI92FullSQL() + "\n" +
                        md.supportsBatchUpdates() + "\n" +
                        md.supportsMixedCaseIdentifiers() + "\n" +
                        md.supportsMultipleTransactions() + "\n" +
                        md.supportsPositionedDelete() + "\n" +
                        md.supportsPositionedUpdate() + "\n" +
                        md.supportsSchemasInDataManipulation() + "\n" +
                        md.supportsTransactions() + "\n" +
                        md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE) + "\n" +
                        md.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE) + "\n" +
                        md.insertsAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE) + "\n" +
                        md.updatesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));

    }

    public static void utworzTablicezPliku() throws SQLException, IOException {
        System.out.println("Loading driver...");
        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11470672";
        String username = "sql11470672";
        String password = "ySC7iFZf6P";
        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");
        Statement stmt = connection.createStatement();
        Items itemki = new Items();
        for (int iterator = 0; iterator < itemki.items.size(); iterator++) {
            String nazwaItemuBezZmiany = itemki.items.get(iterator);
            String nazwaBezSpacjiiApostrofow = nazwaItemuBezZmiany.replace(' ', '_').replace('\'', '_');

            System.out.println(nazwaBezSpacjiiApostrofow);

            stmt.executeUpdate("CREATE TABLE " + nazwaBezSpacjiiApostrofow + " (id MEDIUMINT NOT NULL AUTO_INCREMENT,czas DATETIME,cena_sell MEDIUMINT,cena_buy MEDIUMINT,PRIMARY KEY(id))");

        }

        connection.close();
    }

    public static void zaladujPrzygotowaneZapytanieDoBazy(String zapytanie) throws SQLException, IOException {
        System.out.println("Loading driver...");
        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11470672";
        String username = "sql11470672";
        String password = "ySC7iFZf6P";
        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");
        Statement stmt = connection.createStatement();

        stmt.executeUpdate(zapytanie);
        connection.close();
    }

    public static void usunTablicezPliku() throws SQLException, IOException {
        System.out.println("Loading driver...");
        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11470672";
        String username = "sql11470672";
        String password = "ySC7iFZf6P";
        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");
        Statement stmt = connection.createStatement();
        Items itemki = new Items();
        for (int iterator = 0; iterator < itemki.items.size(); iterator++) {
            String nazwaItemuBezZmiany = itemki.items.get(iterator);
            String nazwaBezSpacjiiApostrofow = nazwaItemuBezZmiany.replace(' ', '_').replace('\'', '_');
            System.out.println(nazwaBezSpacjiiApostrofow);
            stmt.executeUpdate("DROP TABLE " + nazwaBezSpacjiiApostrofow);

        }

        connection.close();
    }

}
