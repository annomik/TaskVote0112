package by.it_academy.jd2.MJD29522.dao.data_base.ds.api;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDataSourceWrapper extends AutoCloseable{

    Connection getConnection() throws SQLException;
}
