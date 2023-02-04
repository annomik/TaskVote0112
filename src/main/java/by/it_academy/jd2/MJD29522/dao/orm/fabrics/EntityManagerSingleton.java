package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.orm.Manager;
import by.it_academy.jd2.MJD29522.dao.orm.api.IManager;

public class EntityManagerSingleton {

    private  volatile static IManager instance;

    private EntityManagerSingleton() {
    }

    public static IManager getInstance() {
        if(instance == null){
            synchronized (EntityManagerSingleton.class){
                if(instance == null){
                    instance = new Manager();
                }
            }
        }
        return instance;
    }
}
