package by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface IDataSourceWrapper extends AutoCloseable{
    Connection getConnection() throws SQLException;
}
