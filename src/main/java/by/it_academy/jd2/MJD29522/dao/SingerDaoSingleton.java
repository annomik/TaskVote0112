package by.it_academy.jd2.MJD29522.dao;


import java.util.ArrayList;
import java.util.List;

public class SingerDaoSingleton  {

   private volatile static SingerDao instance = null;
   private static List<Singer> singers = new ArrayList<Singer>();

   public static SingerDao getInstance(){
      if(instance == null) {
          synchronized (SingerDaoSingleton.class) {
              if (instance == null) {
                  instance = new SingerDao();
                  singers.add(new Singer("Shakira"));
                  singers.add(new Singer("Цой"));
                  singers.add(new Singer("Madonna"));
                  singers.add(new Singer("Linkin Park"));
              }
          }
      }
      return instance;
   }

}