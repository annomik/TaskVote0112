package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.orm.GenreDaoHibernate;

public class GenreHibernateSingleton {

   private volatile static IGenreDao instance;

   private GenreHibernateSingleton(){}

   public static IGenreDao getInstance() {
      if(instance == null){
         synchronized (GenreHibernateSingleton.class){ //.getEntityManager()
            if(instance == null){
                  instance = new GenreDaoHibernate(ManagerSingleton.getInstance());
            }
         }
      }
      return instance;
   }
}