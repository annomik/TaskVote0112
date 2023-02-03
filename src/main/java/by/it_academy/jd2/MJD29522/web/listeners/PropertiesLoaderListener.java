package by.it_academy.jd2.MJD29522.web.listeners;

import by.it_academy.jd2.MJD29522.dao.data_base.ds.api.IDataSourceWrapper;
import by.it_academy.jd2.MJD29522.dao.data_base.ds.fabrics.DataSourceSingleton;
import by.it_academy.jd2.MJD29522.service.fabrics.SendingEmailServiceSingleton;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        File confDir = new File(System.getenv("catalina_base") + "/conf");
        File prop = new File(confDir + "/application.properties");
        try{
            Properties properties = new Properties();
            properties.load(new FileReader(prop));

            DataSourceSingleton.setProperties(properties);
            SendingEmailServiceSingleton.setProperties(properties);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Файл с настройками не найден!", e);
        } catch (IOException e) {
            throw new RuntimeException("Ощибка чтения файла настроек прилажения application.properties", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            IDataSourceWrapper dataSourceWrapper = DataSourceSingleton.getInstance();
            dataSourceWrapper.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
