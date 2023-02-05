package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.service.SendingEmailService;
import by.it_academy.jd2.MJD29522.service.SendingEmailServiceNew;

import java.util.Properties;

public class SendingEmailServiceSingletonNew {
    private static Properties properties;
    private volatile static SendingEmailServiceNew instance;

    private SendingEmailServiceSingletonNew() {}

    public static void setProperties(Properties properties) {
        synchronized (SendingEmailServiceSingletonNew.class){
            if(instance != null){
                throw new IllegalStateException("Нельзя изменять насторойки отправки email при подключении");
            }
            SendingEmailServiceSingletonNew.properties = properties;
        }
    }

    public static SendingEmailServiceNew getInstance() {
        if(instance == null){
            synchronized (SendingEmailServiceSingletonNew.class){
                if(instance == null){
                    instance = new SendingEmailServiceNew(
                            properties,
                            MailServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
