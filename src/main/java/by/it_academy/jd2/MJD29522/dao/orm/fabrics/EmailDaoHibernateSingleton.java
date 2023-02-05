package by.it_academy.jd2.MJD29522.dao.orm.fabrics;

import by.it_academy.jd2.MJD29522.dao.api.IMailDao;
import by.it_academy.jd2.MJD29522.dao.orm.EmailDaoHibernate;
import by.it_academy.jd2.MJD29522.dao.orm.Manager;

public class EmailDaoHibernateSingleton {
    public static volatile IMailDao instance;

    private EmailDaoHibernateSingleton(){}

    public static IMailDao getInstance(){
        if(instance==null){
            synchronized (EmailDaoHibernateSingleton.class){
                if(instance==null){
                    instance = new EmailDaoHibernate(new Manager());
                }
            }
        }
        return instance;
    }
}
