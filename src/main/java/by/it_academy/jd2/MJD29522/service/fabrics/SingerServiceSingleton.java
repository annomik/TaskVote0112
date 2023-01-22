package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.dao.memory.fabrics.SingerDaoMemorySingleton;
import by.it_academy.jd2.MJD29522.dao.provider.SelectedDaoProvider;
import by.it_academy.jd2.MJD29522.service.SingerService;

public class SingerServiceSingleton {
    private volatile static SingerService instance;

   private SingerServiceSingleton() {
    }

    public static SingerService getInstance() {
        if(instance == null){
            synchronized (SingerServiceSingleton.class){
                if(instance == null){
                    instance = new SingerService(SelectedDaoProvider.getInstance().getSingerDao());
                }
            }
        }
        return instance;
    }
}
