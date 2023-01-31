package by.it_academy.jd2.MJD29522.mail.factory;

import by.it_academy.jd2.MJD29522.dao.provider.SelectedDaoProvider;
import by.it_academy.jd2.MJD29522.mail.ServiceSendMail;

public class SendMailServiceSingleton {
    private volatile static ServiceSendMail instance;
    private SendMailServiceSingleton(){}

    public static ServiceSendMail getInstance() {
        if(instance==null){
            synchronized (SendMailServiceSingleton.class){
                if(instance==null){
                    instance = new ServiceSendMail(SelectedDaoProvider.getInstance().getImailDao());
                }
            }
        }
        return instance;
    }
}
