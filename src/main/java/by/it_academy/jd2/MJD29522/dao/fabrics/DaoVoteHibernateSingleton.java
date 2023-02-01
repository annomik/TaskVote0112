package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.DaoVoteHebernate;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.manager.Manager;
import by.it_academy.jd2.MJD29522.dao.manager.api.IManager;
import by.it_academy.jd2.MJD29522.dao.manager.fabrics.ManagerSingleton;

public class DaoVoteHibernateSingleton {
    private static volatile IVoteDao instance;

    private DaoVoteHibernateSingleton() {
    }

    public static IVoteDao getInstance() {
        if(instance==null){
            synchronized (ManagerSingleton.class){
                if(instance==null){
                    instance = new DaoVoteHebernate();
                }
            }
        }
        return instance;
    }
}
