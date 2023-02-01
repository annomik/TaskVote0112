package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.SingerDaoHibernate;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.manager.fabrics.ManagerSingleton;

public class DaoSingerHibernateSingleton {
    private static volatile ISingerDao instance;

    private DaoSingerHibernateSingleton() {
    }

    public static ISingerDao getInstance() {
        if(instance==null){
            synchronized (ManagerSingleton.class){
                if(instance==null){
                    instance = new SingerDaoHibernate();
                }
            }
        }
        return instance;
    }
}
