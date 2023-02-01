package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.service.SendingEmailService;
import java.util.Properties;

public class SendingEmailServiceSingleton {
    private static Properties properties;
    private volatile static SendingEmailService instance;

    private SendingEmailServiceSingleton() {}

    public static void setProperties(Properties properties) {
        synchronized (SendingEmailServiceSingleton.class){
            if(instance != null){
                throw new IllegalStateException("Нельзя изменять насторойки отправки email при подключении");
            }
            SendingEmailServiceSingleton.properties = properties;
        }
    }

    public static SendingEmailService getInstance() {
        if(instance == null){
            synchronized (SendingEmailServiceSingleton.class){
                if(instance == null){
                    instance = new SendingEmailService(properties);
                }
            }
        }
        return instance;
    }
}
