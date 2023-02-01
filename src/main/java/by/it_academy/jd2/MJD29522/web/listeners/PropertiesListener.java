package by.it_academy.jd2.MJD29522.web.listeners;

import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.fabrics.DataSourceSingleton;
import by.it_academy.jd2.MJD29522.mail.api.IServiceSendMail;
import by.it_academy.jd2.MJD29522.mail.factory.MailDaoSingleton;
import by.it_academy.jd2.MJD29522.mail.factory.SendMailServiceSingleton;
import by.it_academy.jd2.MJD29522.model.SelectDao;
import by.it_academy.jd2.MJD29522.util.Select;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PropertiesListener implements ServletContextListener {
    private IServiceSendMail sendMail;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Properties propertiesDB = new Properties();
        Properties propertiesEmail = new Properties();
        if(Select.getSelectDao() == SelectDao.DATABASE){
            File confDir = new File(System.getenv("catalina_base")+"/conf");
            File propDB = new File(confDir+"/applicationDB.properties");
            File propEmail = new File(confDir+"/applicationEmail.properties");
            try {
                propertiesEmail.load(new FileReader(propEmail));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                propertiesDB.load(new FileReader(propDB));
                    DataSourceSingleton.setProperties(propertiesDB);
                    Select.setMemoryDataBase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        sendMail = SendMailServiceSingleton.getInstance(propertiesEmail,true);
        executorService.submit(new Thread(() -> sendMail.sendAllMailThroughHour()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if(sendMail!=null){
            sendMail.setSendMassage(false);
        }
        //        try {
//            DataSourceSingleton.getInstance().close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}
