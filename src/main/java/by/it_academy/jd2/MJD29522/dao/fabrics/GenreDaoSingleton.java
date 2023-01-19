package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.memory.GenreDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.GenreDaoDB;
import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.util.SelectBDOrSQL;

public class GenreDaoSingleton  {

   private volatile static IGenreDao instance;

   private GenreDaoSingleton (){};

   public static IGenreDao getInstance(){
      if(instance == null){
         synchronized (GenreDaoSingleton.class){
            if(instance == null){
               if(SelectBDOrSQL.getSelectSQL()){
                  instance = new GenreDaoDB();
               } else {
                  instance = new GenreDao();
               }
            }
         }
      }
      return instance;
   }
}