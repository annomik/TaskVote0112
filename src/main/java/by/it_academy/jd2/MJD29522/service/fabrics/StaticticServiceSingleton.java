package by.it_academy.jd2.MJD29522.service.fabrics;
//REMOVE
//REMOVE
//REMOVE
//REMOVE
//REMOVE
import by.it_academy.jd2.MJD29522.dao.fabrics.VoteDaoSingleton;
import by.it_academy.jd2.MJD29522.service.VoteService;

public class StaticticServiceSingleton {
    private volatile static VoteService instance;

   private StaticticServiceSingleton() {
    }

    public static VoteService getInstance() {
        if(instance == null){
            synchronized (StaticticServiceSingleton.class){
                if(instance == null){
                    instance = new VoteService(
                            VoteDaoSingleton.getInstance(),
                            SingerServiceSingleton.getInstance(),
                            GenreServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }



}
