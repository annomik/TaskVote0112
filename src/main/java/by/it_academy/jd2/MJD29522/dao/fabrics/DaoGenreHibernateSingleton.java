package by.it_academy.jd2.MJD29522.dao.fabrics;

import by.it_academy.jd2.MJD29522.dao.GenreDaoHibernate;
import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.manager.fabrics.ManagerSingleton;

public class DaoGenreHibernateSingleton {
    private static volatile IGenreDao instance;

    private DaoGenreHibernateSingleton() {
    }

    public static IGenreDao getInstance() {
        if(instance==null){
            synchronized (DaoGenreHibernateSingleton.class){
                if(instance==null){
                    instance = new GenreDaoHibernate(ManagerSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
