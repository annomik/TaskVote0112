package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.util.StartingDB;
public class SrartingDBSingleton {

   private volatile static StartingDB instance;

   private SrartingDBSingleton(){};

   public static StartingDB getInstance(){
      if(instance == null){
         synchronized (SrartingDBSingleton.class){
            if(instance == null){
               instance = new StartingDB();
            }
         }
      }
      return instance;
   }
}