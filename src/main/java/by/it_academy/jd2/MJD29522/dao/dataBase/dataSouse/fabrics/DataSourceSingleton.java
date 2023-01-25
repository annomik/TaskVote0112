package by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.fabrics;

import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.DataSouseC3P0;
import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.api.IDataSourceWrapper;

import java.beans.PropertyVetoException;
import java.util.Properties;

public class DataSourceSingleton {
    private volatile static IDataSourceWrapper instance;
    private static volatile Properties properties;
    private DataSourceSingleton(){}

    public static IDataSourceWrapper getInstance() throws PropertyVetoException {
//        if(properties==null){
//            throw new IllegalStateException("Настроки не установлены");
  //      }
        if(instance==null){
            synchronized (DataSourceSingleton.class){
                if(instance==null){
                    instance = new DataSouseC3P0(properties);
                }
            }
        }
        return instance;
    }

    public static void setProperties(Properties property) {
        synchronized (DataSourceSingleton.class) {
            if (properties == null){
                DataSourceSingleton.properties = property;
            } else {
                throw new IllegalStateException("Настройки для подключения к БД установлены");
            }
        }
    }
}
