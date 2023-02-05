package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.dao.orm.EmailDaoHibernate;
import by.it_academy.jd2.MJD29522.dao.orm.fabrics.EmailDaoHibernateSingleton;
import by.it_academy.jd2.MJD29522.service.MailService;
import by.it_academy.jd2.MJD29522.service.api.IMailService;

public class MailServiceSingleton {
    private static volatile IMailService instance;
    private MailServiceSingleton(){}

    public static IMailService getInstance() {
        if(instance == null){
            synchronized (MailServiceSingleton.class){
                if(instance == null){
                    instance = new MailService(
                            SingerServiceSingleton.getInstance(),
                            GenreServiceSingleton.getInstance(),
                            EmailDaoHibernateSingleton.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}
