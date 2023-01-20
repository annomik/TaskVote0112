package by.it_academy.jd2.MJD29522.dao.memory.fabrics;

import by.it_academy.jd2.MJD29522.dao.dataBase.ds.api.IDataSourceWrapper;
import by.it_academy.jd2.MJD29522.dao.memory.SingerDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.SingerDaoDB;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.provider.api.IDaoProvider;
import by.it_academy.jd2.MJD29522.util.SelectBDOrSQL;

public class SingerDaoMemorySingleton {

   private volatile static ISingerDao instance;

    private SingerDaoMemorySingleton() {    }

    public static ISingerDao getInstance(){
      if(instance == null) {
          synchronized (SingerDaoMemorySingleton.class) {
              if (instance == null) {
                  instance = new SingerDao();
              }
          }
      }
      return instance;
   }

}