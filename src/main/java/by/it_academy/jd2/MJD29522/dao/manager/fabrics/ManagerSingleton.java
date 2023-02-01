package by.it_academy.jd2.MJD29522.dao.manager.fabrics;

import by.it_academy.jd2.MJD29522.dao.manager.Manager;
import by.it_academy.jd2.MJD29522.dao.manager.api.IManager;

public class ManagerSingleton {
    private static volatile IManager instance;

    private ManagerSingleton() {
    }

    public static IManager getInstance() {
        if(instance==null){
            synchronized (ManagerSingleton.class){
                if(instance==null){
                    instance = new Manager();
                }
            }
        }
        return instance;
    }
}
