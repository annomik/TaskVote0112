package by.it_academy.jd2.MJD29522.dao.dataBase.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.GenreDaoDB;
import by.it_academy.jd2.MJD29522.dao.dataBase.ds.fabrics.DataSourceSingleton;

import java.beans.PropertyVetoException;

public class GenreDaoDBSingleton {

   private volatile static IGenreDao instance;

   private GenreDaoDBSingleton(){};

   public static IGenreDao getInstance() throws PropertyVetoException {
      if(instance == null){
         synchronized (GenreDaoDBSingleton.class){
            if(instance == null){
                  instance = new GenreDaoDB(DataSourceSingleton.getInstance());
            }
         }
      }
      return instance;
   }
}