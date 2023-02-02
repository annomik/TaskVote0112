package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.orm.SingerDaoHibernate;

public class SingerHibernateSingleton {

   private volatile static ISingerDao instance;

   private SingerHibernateSingleton(){}

   public static ISingerDao getInstance()  {
      if(instance == null){
         synchronized (SingerHibernateSingleton.class){
            if(instance == null){
                  instance = new SingerDaoHibernate(ManagerSingleton.getInstance());
            }
         }
      }
      return instance;
   }
}