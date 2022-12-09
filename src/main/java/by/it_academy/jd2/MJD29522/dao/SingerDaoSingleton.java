package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.service.fabrics.SingerServiceSingleton;

public class SingerDaoSingleton  {

   private volatile static SingerDao instance;

    public SingerDaoSingleton() {
    }

    public static ISingerDao getInstance(){
      if(instance == null) {
          synchronized (SingerServiceSingleton.class) {
              if (instance == null) {
                  instance = new SingerDao();

              }
          }
      }
      return instance;
   }

}