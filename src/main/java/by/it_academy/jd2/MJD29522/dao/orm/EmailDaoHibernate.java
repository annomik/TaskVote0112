package by.it_academy.jd2.MJD29522.dao.orm;

import by.it_academy.jd2.MJD29522.dao.api.IMailDao;
import by.it_academy.jd2.MJD29522.dao.orm.api.IManager;
import by.it_academy.jd2.MJD29522.dao.orm.entity.EmailEntity;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;

public class EmailDaoHibernate implements IMailDao {
    private final IManager manager;
    public EmailDaoHibernate(IManager manager){
        this.manager = manager;
    }
    @Override
    public List<EmailEntity> getEmails() {
        List<EmailEntity> emails;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            emails = entityManager.createQuery
                    ("from EmailEntity ORDER BY lastSendTime",EmailEntity.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return emails;
    }

    public List<EmailEntity> getEmailsForSend() {
        List<EmailEntity> emails = getEmails();
        List<EmailEntity> emailForSend = new LinkedList<>();
        for(EmailEntity email : emails){
            if((email.isValidateEmail())&&(email.isSendMassage())){
                emailForSend.add(email);
            }
        }
        return emailForSend;
    }

    @Override
    public EmailEntity getEmail(long id) {
        EmailEntity email;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            email = entityManager.find(EmailEntity.class,id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return email;
    }

    @Override
    public boolean addEmail(EmailEntity email) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(email);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return true;
    }

    @Override
    public boolean updateEmail(long id, String message, boolean validateEmail,
                               boolean sendMassage, long lastSendTime, String email) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            EmailEntity emailDao = entityManager.find(EmailEntity.class,id);
            if(emailDao==null){
                return false;
            }
            emailDao.setMessage(message);
            emailDao.setValidateEmail(validateEmail);
            emailDao.setSendMassage(sendMassage);
            emailDao.setLastSendTime(System.currentTimeMillis());
            emailDao.setEmail(email);
            entityManager.merge(emailDao);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return true;
    }

    @Override
    public boolean deleteEmail(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            EmailEntity email = entityManager.find(EmailEntity.class,id);
            if(email!=null){
                entityManager.remove(email);
            } else {
                return false;
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return true;
    }
}
