package by.it_academy.jd2.MJD29522.dao;


import java.util.ArrayList;
import java.util.List;

public class VoteDaoSingleton {

   private volatile static VoteDao instance = null;
   private static List<Vote> votes = new ArrayList<Vote>();

   public static VoteDao getInstance(){
      if(instance == null) {
          synchronized (VoteDaoSingleton.class) {
              if (instance == null) {
                  instance = new VoteDao();
             //     singers.add(new Vote("Shakira"));

              }
          }
      }
      return instance;
   }

}