package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.GenreDaoDB;
import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;

public class GenreDaoDBSingleton {

   private volatile static GenreDaoDB instance;
   private GenreDaoDBSingleton(){};
   public static IGenreDao getInstance(){
      if(instance == null){
         synchronized (GenreDaoDBSingleton.class){
            if(instance == null){
               instance = new GenreDaoDB();
            }
         }
      }
      return instance;
   }
}