package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.orm.api.IManager;
import by.it_academy.jd2.MJD29522.dao.orm.Manager;

public class ManagerSingleton {
   private volatile static Manager instance;

   private ManagerSingleton(){}

   public static IManager getInstance() {
      if(instance == null){
         synchronized (ManagerSingleton.class){
            if(instance == null){
               instance = new Manager();
            }
         }
      }
      return instance;
   }
}