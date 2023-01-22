package by.it_academy.jd2.MJD29522.dao.memory.fabrics;

import by.it_academy.jd2.MJD29522.dao.memory.SingerDaoMemory;
import by.it_academy.jd2.MJD29522.dao.dataBase.SingerDaoDB;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.util.Select;

public class SingerDaoMemorySingleton {

   private volatile static ISingerDao instance;

    private SingerDaoMemorySingleton() {    }

    public static ISingerDao getInstance(){
      if(instance == null) {
          synchronized (SingerDaoMemorySingleton.class) {
              if (instance == null) {
                      instance = new SingerDaoMemory();
              }
          }
      }
      return instance;
   }

}