package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.SingerDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.service.fabrics.SingerServiceSingleton;

public class SingerDaoSingleton  {

   private volatile static SingerDao instance;

    private SingerDaoSingleton() {
    }

    public static ISingerDao getInstance(){
      if(instance == null) {
          synchronized (SingerDaoSingleton.class) {
              if (instance == null) {
                  instance = new SingerDao();
              }
          }
      }
      return instance;
   }

}