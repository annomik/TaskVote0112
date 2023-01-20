package by.it_academy.jd2.MJD29522.dao.memory.fabrics;

import by.it_academy.jd2.MJD29522.dao.dataBase.ds.fabrics.DataSourceSingleton;
import by.it_academy.jd2.MJD29522.dao.memory.GenreDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.GenreDaoDB;
import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.util.SelectBDOrSQL;

import java.beans.PropertyVetoException;

public class GenreDaoMemorySingleton {

   private volatile static IGenreDao instance;

   private GenreDaoMemorySingleton(){};

   public static IGenreDao getInstance() {
      if(instance == null){
         synchronized (GenreDaoMemorySingleton.class){
            if(instance == null){
               instance = new GenreDao();
            }
         }
      }
      return instance;
   }
}