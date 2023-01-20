package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.dao.provider.ChoiceDaoProvider;
import by.it_academy.jd2.MJD29522.service.GenreService;

public class GenreServiceSingleton {
    private volatile static GenreService instance;

   private GenreServiceSingleton() {
    }

        public static GenreService getInstance() {
        if(instance == null){
            synchronized (GenreServiceSingleton.class){
                if(instance == null){
                    instance = new GenreService(ChoiceDaoProvider.getInstance().getGenreDao());
                }
            }
        }
        return instance;
    }
}
