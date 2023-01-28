package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.VoteDaoDB;
import by.it_academy.jd2.MJD29522.dao.dataBase.ds.fabrics.DataSourceSingleton;

import java.beans.PropertyVetoException;

public class VoteHibernateSingleton {

   private volatile static IVoteDao instance;

   private VoteHibernateSingleton(){}

   public static IVoteDao getInstance() throws PropertyVetoException {
      if(instance == null){
         synchronized (VoteHibernateSingleton.class){
            if(instance == null){
                  instance = new VoteDaoDB(DataSourceSingleton.getInstance());
            }
         }
      }
      return instance;
   }
}