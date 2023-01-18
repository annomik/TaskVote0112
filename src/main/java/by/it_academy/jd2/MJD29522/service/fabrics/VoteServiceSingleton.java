package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.dao.fabrics.VoteDaoSingleton;
import by.it_academy.jd2.MJD29522.service.VoteService;

public class VoteServiceSingleton {
    private volatile static VoteService instance;

   private VoteServiceSingleton() {
    }

    public static VoteService getInstance() {
        if(instance == null){
            synchronized (VoteServiceSingleton.class){
                if(instance == null){
                    instance = new VoteService(
                            VoteDaoSingleton.getInstance(),
                            SingerServiceSingleton.getInstance(),
                            GenreServiceSingleton.getInstance(),
                            SendingEmailServiceSingleton.getInstance() );
                }
            }
        }
        return instance;
    }



}
