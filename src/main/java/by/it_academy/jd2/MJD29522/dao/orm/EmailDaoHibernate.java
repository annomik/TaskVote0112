package by.it_academy.jd2.MJD29522.dao.orm;

import by.it_academy.jd2.MJD29522.dao.api.IMailDao;
import by.it_academy.jd2.MJD29522.dao.orm.api.IManager;
import by.it_academy.jd2.MJD29522.dao.orm.entity.EmailEntity;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailDaoHibernate implements IMailDao {
    private final IManager manager;
    public EmailDaoHibernate(IManager manager){
        this.manager = manager;
    }
    @Override
    public List<EmailEntity> getEmails() {
        List<EmailEntity> emails = new ArrayList<>();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        return emails;
    }

    @Override
    public EmailEntity getEmail(long id) {
        return null;
    }

    @Override
    public boolean addEmail(EmailEntity email) {
        return false;
    }

    @Override
    public boolean updateEmail(long id, EmailEntity email) {
        return false;
    }

    @Override
    public boolean deleteEmail(long id) {
        return false;
    }
}
