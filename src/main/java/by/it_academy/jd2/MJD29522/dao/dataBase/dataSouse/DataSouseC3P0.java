package by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse;

import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.api.IDataSourceWrapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSouseC3P0 implements IDataSourceWrapper {
    private final String DRIVER_CLASS_PROPERTY = "dataBase.class";
    private final String URL_CONNECTION_PROPERTY = "dataBase.url";
    private final String USER_PROPERTY = "dataBase.user";
    private final String PASSWORD_PROPERTY = "dataBase.password";
    private ComboPooledDataSource ds;

    public DataSouseC3P0(Properties properties) throws PropertyVetoException {
        this.ds = new ComboPooledDataSource();
        this.ds.setDriverClass(properties.getProperty(DRIVER_CLASS_PROPERTY));
        this.ds.setJdbcUrl(properties.getProperty(URL_CONNECTION_PROPERTY));
        this.ds.setUser(properties.getProperty(USER_PROPERTY));
        this.ds.setPassword(PASSWORD_PROPERTY);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }

    @Override
    public void close() throws Exception {
        this.ds.close();
    }
}
