package by.it_academy.jd2.MJD29522.dao.manager.api;

import javax.persistence.EntityManager;

public interface IManager {
    EntityManager getEntityManagerFactory();
    void close();
}
