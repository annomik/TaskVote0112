package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.SingerDao;
import by.it_academy.jd2.MJD29522.dao.SingerDaoDB;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.service.fabrics.SingerServiceSingleton;
import by.it_academy.jd2.MJD29522.util.SelectBDOrSQL;

public class SingerDaoSingleton  {

   private volatile static ISingerDao instance;

    private SingerDaoSingleton() {
    }

    public static ISingerDao getInstance(){
      if(instance == null) {
          synchronized (SingerDaoSingleton.class) {
              if (instance == null) {
                  if(SelectBDOrSQL.getSelectSQL()){
                      instance = new SingerDaoDB();
                  } else {
                      instance = new SingerDao();
                  }
              }
          }
      }
      return instance;
   }

}