package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.VoteDao;
import by.it_academy.jd2.MJD29522.dao.VoteDaoDB;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import util.SelectBDOrSQL;

public class VoteDaoSingleton {

    private volatile static IVoteDao instance;

    private VoteDaoSingleton() {
    }

    public static IVoteDao getInstance(){
      if(instance == null) {
          synchronized (VoteDaoSingleton.class) {
              if (instance == null) {
                  if(SelectBDOrSQL.getSelectSQL()){
                      instance = new VoteDaoDB();
                  } else {
                      instance = new VoteDao();
                  }
              }
          }
      }
      return instance;
   }

}