package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.orm.VoteDaoHibernate;

public class VoteDaoHibernateSingleton {
    private volatile static IVoteDao instance;

    private VoteDaoHibernateSingleton() {
    }

    public static IVoteDao getInstance() {
        if(instance == null){
            synchronized (VoteDaoHibernateSingleton.class){
                if(instance == null){
                    instance = new VoteDaoHibernate(EntityManagerSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
