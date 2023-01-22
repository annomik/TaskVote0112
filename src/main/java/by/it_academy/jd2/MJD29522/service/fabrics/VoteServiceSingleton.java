package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.dao.memory.fabrics.VoteDaoMemorySingleton;
import by.it_academy.jd2.MJD29522.dao.provider.SelectedDaoProvider;
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
                            SelectedDaoProvider.getInstance().getIVoteDAo(),
                            SingerServiceSingleton.getInstance(),
                            GenreServiceSingleton.getInstance(),
                            SendingEmailServiceSingleton.getInstance() );
                }
            }
        }
        return instance;
    }



}
