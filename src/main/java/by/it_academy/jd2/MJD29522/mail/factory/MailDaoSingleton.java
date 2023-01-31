package by.it_academy.jd2.MJD29522.mail.factory;

import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.fabrics.DataSourceSingleton;
import by.it_academy.jd2.MJD29522.mail.MailDao;

import java.beans.PropertyVetoException;

public class MailDaoSingleton {
    private static volatile MailDao instance;

    private MailDaoSingleton() {
    }

    public static MailDao getInstance() throws PropertyVetoException {
        if(instance==null){
            synchronized (MailDaoSingleton.class){
                if(instance==null){
                    instance = new MailDao(DataSourceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
