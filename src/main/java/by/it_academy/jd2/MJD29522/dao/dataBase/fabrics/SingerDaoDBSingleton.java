package by.it_academy.jd2.MJD29522.dao.dataBase.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.SingerDaoDB;
import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.fabrics.DataSourceSingleton;

import java.beans.PropertyVetoException;

public class SingerDaoDBSingleton {
    private volatile static ISingerDao instance;

    private SingerDaoDBSingleton() {
    }

    public static ISingerDao getInstance() throws PropertyVetoException {
        if(instance==null){
            synchronized (SingerDaoDBSingleton.class){
                if(instance==null){
                    instance = new SingerDaoDB(DataSourceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
