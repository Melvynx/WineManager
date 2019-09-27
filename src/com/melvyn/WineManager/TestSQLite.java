package com.melvyn.WineManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSQLite {
    public static void main(String[] args) {
        Connexion connexion = new Connexion("src/com/melvyn/WineManager/Alcohol.db");
        connexion.connect();
        connexion.createTable();
        ResultSet resultSet = connexion.query("SELECT * FROM BOOK");
        try{
            while(resultSet.next()){
                System.out.println("Titre : "+resultSet.getString("Title"));
                System.out.println("Pages : "+resultSet.getString("Pages"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();

    }
}
