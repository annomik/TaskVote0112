package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.GenreDao;
import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;

public class GenreDaoSingleton  {

   private volatile static GenreDao instance;
   private GenreDaoSingleton (){};
   public static IGenreDao getInstance(){
      if(instance == null){
         synchronized (GenreDaoSingleton.class){
            if(instance == null){
               instance = new GenreDao();
            }
         }
      }
      return instance;
   }
}