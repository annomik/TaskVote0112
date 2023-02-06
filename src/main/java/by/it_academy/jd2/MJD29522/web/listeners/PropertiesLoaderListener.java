package by.it_academy.jd2.MJD29522.web.listeners;

import by.it_academy.jd2.MJD29522.service.SendingEmailServiceNew;
import by.it_academy.jd2.MJD29522.service.fabrics.SendingEmailServiceSingleton;
import by.it_academy.jd2.MJD29522.service.fabrics.SendingEmailServiceSingletonNew;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PropertiesLoaderListener implements ServletContextListener {
    private Properties properties = new Properties();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        File confDir = new File(System.getenv("catalina_base") + "/conf");
        File prop = new File(confDir + "/application.properties");
        Properties properties = new Properties();
        try{
            properties.load(new FileReader(prop));

            SendingEmailServiceSingleton.setProperties(properties);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Файл с настройками не найден!", e);
        } catch (IOException e) {
            throw new RuntimeException("Ощибка чтения файла настроек прилажения application.properties", e);
        }
       SendingEmailServiceNew sendingEmailService = SendingEmailServiceSingletonNew.getInstance(properties);
       ExecutorService executorService = Executors.newFixedThreadPool(1);
       executorService.submit(new Runnable() {
           @Override
           public void run() {
               sendingEmailService.startEmailSender(15);
           }
       });
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SendingEmailServiceNew sendingEmailService = SendingEmailServiceSingletonNew.getInstance(properties);
        sendingEmailService.stopEmailSender();
    }
}
