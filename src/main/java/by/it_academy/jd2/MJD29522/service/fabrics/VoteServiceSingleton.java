package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.dao.VoteDaoSingleton;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.VoteService;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;

public class VoteServiceSingleton {
    private volatile static VoteService instance;

   private VoteServiceSingleton() {
    }

    public static VoteService getInstance() {
        if(instance == null){
            synchronized (VoteServiceSingleton.class){
                if(instance == null){
                    instance = new VoteService(VoteDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }



}
