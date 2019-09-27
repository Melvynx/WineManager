package com.melvyn.WineManager;

import java.sql.*;

public class Connexion {
    private String DBPath = "Chemin au base de donnée SQLite.";
    private Connection connection = null;
    private Statement statement = null;

    public Connexion(String dbPath) {
        DBPath = dbPath;

    }
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connecxion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connecxion");
        }
    }
    public void close() {
        try{
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException n) {
            System.out.println("Erreur de connexion.");
        }
    }
    public ResultSet query(String requet) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(requet);
        } catch (SQLException a) {
            a.printStackTrace();
            System.out.println("Erreur dans la requet : "+ requet);
        }
        return resultat;
    }
    public void addBook(Book book) {
        /*
        String query = "";
        query += "INSERT INTO BOOK VALUES (";
        query += "'" + book.getBookId() + "', ";
        query += "'" + book.getTitle() + "', ";
        query += "'" + book.getSubTitle() + "', ";
        query += book.getPages() + ", ";
        query += "'" + book.getPublished().toString()+ "', ";
        query += "'" + book.getDescription() + "' )";
         */
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Book VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1, book.getBookId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getSubTitle());
            preparedStatement.setInt(4, book.getPages());
            preparedStatement.setDate(5, (Date) book.getPublished());
            preparedStatement.setString(6, book.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Alcohol (\n" +
                "    id SMALLINT UNSIGNED NOT NULL,\n" +
                "    name VARCHAR(40) NOT NULL,\n" +
                "    region VARCHAR(40),\n" +
                "    age INTEGER NOT NULL,\n" +
                "    degreeOfAlcohol INTEGER NOT NULL,\n" +
                "    capacityML INTEGER NOT NULL\n" +
                ");";
        if (connection == null) {
            return;
        }
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
