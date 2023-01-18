package by.it_academy.jd2.MJD29522.service.fabrics;

import by.it_academy.jd2.MJD29522.service.SendingEmailService;

public class SendingEmailServiceSingleton {
    private volatile static SendingEmailService instance;

    private SendingEmailServiceSingleton() {}

    public static SendingEmailService getInstance() {
        if(instance == null){
            synchronized (SendingEmailServiceSingleton.class){
                if(instance == null){
                    instance = new SendingEmailService();
                }
            }
        }
        return instance;
    }



}
