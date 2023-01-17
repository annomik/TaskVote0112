package by.it_academy.jd2.MJD29522.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.*;

public class StartingDB {
    private final String URL = "jdbc:postgresql://localhost:5432/voting";
    private final String LOG = "postgres";
    private final String PAS = "postgres";

    ComboPooledDataSource cpds = new ComboPooledDataSource();

    public StartingDB() {
        try{
            cpds.setDriverClass( "org.postgresql.Driver" );
        }catch (PropertyVetoException e){
            throw new RuntimeException(e);
        }
    }
    public Connection load () throws SQLException {
        cpds.setJdbcUrl(URL);
        cpds.setUser(LOG);
        cpds.setPassword(PAS);
        return cpds.getConnection();
    }
}
