package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.DaoMailHibernate;
import by.it_academy.jd2.MJD29522.dao.api.IMailDao;
import by.it_academy.jd2.MJD29522.dao.manager.Manager;
import by.it_academy.jd2.MJD29522.dao.manager.api.IManager;
import by.it_academy.jd2.MJD29522.dao.manager.fabrics.ManagerSingleton;

public class DaoMailHibernateSingleton {
    private static volatile IMailDao instance;

    private DaoMailHibernateSingleton() {
    }

    public static IMailDao getInstance() {
        if(instance==null){
            synchronized (ManagerSingleton.class){
                if(instance==null){
                    instance = new DaoMailHibernate();
                }
            }
        }
        return instance;
    }
}
