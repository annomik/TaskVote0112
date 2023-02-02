package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.orm.VoteDaoHibernate;

public class VoteHibernateSingleton {

   private volatile static IVoteDao instance;

   private VoteHibernateSingleton(){}

   public static IVoteDao getInstance()  {
      if(instance == null){
         synchronized (VoteHibernateSingleton.class){
            if(instance == null){
                  instance = new VoteDaoHibernate(ManagerSingleton.getInstance());
            }
         }
      }
      return instance;
   }
}