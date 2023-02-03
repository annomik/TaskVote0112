package by.it_academy.jd2.MJD29522.dao.data_base.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.data_base.VoteDaoDB;
import by.it_academy.jd2.MJD29522.dao.data_base.ds.fabrics.DataSourceSingleton;
import java.beans.PropertyVetoException;

public class VoteDaoDBSingleton {

   private volatile static IVoteDao instance;

   private VoteDaoDBSingleton(){};

   public static IVoteDao getInstance() throws PropertyVetoException {
      if(instance == null){
         synchronized (VoteDaoDBSingleton.class){
            if(instance == null){
                  instance = new VoteDaoDB(DataSourceSingleton.getInstance());
            }
         }
      }
      return instance;
   }
}