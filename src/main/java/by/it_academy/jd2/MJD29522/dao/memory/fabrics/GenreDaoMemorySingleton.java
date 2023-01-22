package by.it_academy.jd2.MJD29522.dao.memory.fabrics;

import by.it_academy.jd2.MJD29522.dao.memory.GenreDaoMemory;
import by.it_academy.jd2.MJD29522.dao.dataBase.GenreDaoDB;
import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.util.Select;

public class GenreDaoMemorySingleton {

   private volatile static IGenreDao instance;

   private GenreDaoMemorySingleton(){};

   public static IGenreDao getInstance(){
      if(instance == null){
         synchronized (GenreDaoMemorySingleton.class){
            if(instance == null){
                  instance = new GenreDaoMemory();
            }
         }
      }
      return instance;
   }
}