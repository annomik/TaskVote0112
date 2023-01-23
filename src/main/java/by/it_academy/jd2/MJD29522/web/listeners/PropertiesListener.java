package by.it_academy.jd2.MJD29522.web.listeners;

import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.fabrics.DataSourceSingleton;
import by.it_academy.jd2.MJD29522.model.SelectDao;
import by.it_academy.jd2.MJD29522.util.Select;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesListener implements ServletContextListener {
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
                if(propDB!=null){
                    DataSourceSingleton.setProperties(propertiesDB);
                } else {
                    Select.setMemoryDataBase();
                    throw new IllegalStateException("Файл настроки для подключения к БД не зополнен");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            DataSourceSingleton.getInstance().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
