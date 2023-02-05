package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.dao.orm.fabrics.EmailDaoHibernateSingleton;
import by.it_academy.jd2.MJD29522.dao.provider.ChoiceDaoProvider;
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
                            ChoiceDaoProvider.getInstance().getMailDao()
                    );
                }
            }
        }
        return instance;
    }
}
