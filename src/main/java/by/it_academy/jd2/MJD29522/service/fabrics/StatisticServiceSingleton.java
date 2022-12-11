package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.service.StatisticService;

public class StatisticServiceSingleton {
   private volatile static StatisticService instance;

   private StatisticServiceSingleton() {
   }

   public static StatisticService getInstance() {
       if(instance == null){
           synchronized (StatisticServiceSingleton.class){
               if(instance == null){
                   instance = new StatisticService(SingerServiceSingleton.getInstance(),
                           GenreServiceSingleton.getInstance(),
                           VoteServiceSingleton.getInstance());
               }
           }
       }
       return instance;
   }
}
