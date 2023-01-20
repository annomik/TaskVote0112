package by.it_academy.jd2.MJD29522.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class PropertiesLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        File confDir = new File(System.getenv("catalina.base" + "/conf"));
        File prop = new File(confDir + "/*application.properties");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
