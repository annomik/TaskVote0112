package by.it_academy.jd2.MJD29522.dao.dataBase.ds.fabrics;

import by.it_academy.jd2.MJD29522.dao.dataBase.ds.DataSourceC3P0;
import by.it_academy.jd2.MJD29522.dao.dataBase.ds.api.IDataSourceWrapper;

import java.beans.PropertyVetoException;
import java.util.Properties;

public class DataSourceSingleton {

   private static Properties properties;

   private volatile static IDataSourceWrapper instance;

   private DataSourceSingleton(){};

   public static void setProperties(Properties properties) {
      synchronized (DataSourceSingleton.class){
         if(instance != null){
            throw new IllegalStateException("Нельзя изменять насторойки подключения БД при подключении");
         }
         DataSourceSingleton.properties = properties;
      }
   }

   public static IDataSourceWrapper getInstance() throws PropertyVetoException {
      if(instance == null){
         synchronized (DataSourceSingleton.class){
            if(instance == null){
               instance = new DataSourceC3P0(properties);
            }
         }
      }
      return instance;
   }
}