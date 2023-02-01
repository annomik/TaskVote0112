package by.it_academy.jd2.MJD29522.dao.manager;

import by.it_academy.jd2.MJD29522.dao.manager.api.IManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager implements IManager {
    private EntityManagerFactory entityManagerFactory;

    public Manager(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("my manager");
    }

    @Override
    public EntityManager getEntityManagerFactory() {
        return this.entityManagerFactory.createEntityManager();
    }

    @Override
    public void close() {
        this.entityManagerFactory.close();;
    }
}
