package by.it_academy.jd2.MJD29522.dao.memory.fabrics;

import by.it_academy.jd2.MJD29522.dao.memory.VoteDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.VoteDaoDB;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.util.SelectBDOrSQL;

public class VoteDaoMemorySingleton {

    private volatile static IVoteDao instance;

    private VoteDaoMemorySingleton() {
    }

    public static IVoteDao getInstance(){
      if(instance == null) {
          synchronized (VoteDaoMemorySingleton.class) {
              if (instance == null) {
                  instance = new VoteDao();
              }
          }
      }
      return instance;
   }

}