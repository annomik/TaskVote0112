package by.it_academy.jd2.MJD29522.dao.memory.fabrics;

import by.it_academy.jd2.MJD29522.dao.memory.VoteDaoMemory;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;

public class VoteDaoMemorySingleton {

    private volatile static IVoteDao instance;

    private VoteDaoMemorySingleton() {
    }

    public static IVoteDao getInstance(){
      if(instance == null) {
          synchronized (VoteDaoMemorySingleton.class) {
                      instance = new VoteDaoMemory();
          }
      }
      return instance;
   }

}