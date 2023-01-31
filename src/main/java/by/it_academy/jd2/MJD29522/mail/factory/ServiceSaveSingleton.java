package by.it_academy.jd2.MJD29522.mail.factory;

import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.DataSouseC3P0;
import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.fabrics.DataSourceSingleton;
import by.it_academy.jd2.MJD29522.dao.provider.SelectedDaoProvider;
import by.it_academy.jd2.MJD29522.mail.MailDao;
import by.it_academy.jd2.MJD29522.mail.ServiceMail;
import by.it_academy.jd2.MJD29522.service.fabrics.GenreServiceSingleton;
import by.it_academy.jd2.MJD29522.service.fabrics.SingerServiceSingleton;

import java.beans.PropertyVetoException;
import java.security.Provider;
import java.util.Properties;

public class ServiceSaveSingleton {
    private volatile static ServiceMail instance;

    private ServiceSaveSingleton(){}

    public static ServiceMail getInstance() throws PropertyVetoException {
        if(instance==null){
            synchronized (ServiceSaveSingleton.class){
                if(instance==null){
                    instance = new ServiceMail(
                            SelectedDaoProvider.getInstance().getImailDao(),
                            SingerServiceSingleton.getInstance(),
                            GenreServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
