package by.it_academy.jd2.MJD29522.dao;

import java.sql.*;

public  class StartingDB {

    private final String URL = "jdbc:postgresql://localhost:5432/voiting";
    private final String LOG = "postgres";
    private final String PAS = "postgres";

    public StartingDB() {
        try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    public Connection load () throws SQLException {
        return DriverManager.getConnection(URL, LOG, PAS);
    }
}
