package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.orm.GenreDaoHibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenreDaoHibernateSingleton {

   private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("tutorial");
   private volatile static IGenreDao instance;

   private GenreDaoHibernateSingleton(){};

   public static IGenreDao getInstance() {
      if(instance == null){
         synchronized (GenreDaoHibernateSingleton.class){
            if(instance == null){
                  instance = new GenreDaoHibernate(factory);
            }
         }
      }
      return instance;
   }
}