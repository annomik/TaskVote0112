package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.orm.GenreDaoHibernate;

public class GenreDaoHibernateSingleton {
    private volatile static IGenreDao instance;

    private GenreDaoHibernateSingleton() {
    }

    public static IGenreDao getInstance() {
        if(instance == null){
            synchronized (GenreDaoHibernateSingleton.class){
                if(instance == null){
                    instance = new GenreDaoHibernate(EntityManagerSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
