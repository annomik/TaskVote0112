package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.VoteDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.service.fabrics.VoteServiceSingleton;

public class VoteDaoSingleton {

    private volatile static VoteDao instance;

    private VoteDaoSingleton() {
    }

    public static IVoteDao getInstance(){
      if(instance == null) {
          synchronized (VoteServiceSingleton.class) {
              if (instance == null) {
                  instance = new VoteDao();
              }
          }
      }
      return instance;
   }

}