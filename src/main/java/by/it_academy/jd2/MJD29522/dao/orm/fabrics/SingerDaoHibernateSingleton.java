package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.orm.SingerDaoHibernate;

public class SingerDaoHibernateSingleton {
    private volatile static ISingerDao instance;

    private SingerDaoHibernateSingleton() {
    }

    public static ISingerDao getInstance() {
        if(instance == null){
            synchronized (SingerDaoHibernateSingleton.class){
                if(instance == null){
                    instance = new SingerDaoHibernate(EntityManagerSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
