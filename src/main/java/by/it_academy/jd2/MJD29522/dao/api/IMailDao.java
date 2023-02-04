package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dao.orm.entity.EmailEntity;

import java.util.List;

public interface IMailDao {
    List<EmailEntity> getEmails();
    EmailEntity getEmail(long id);
    boolean addEmail(EmailEntity email);
    boolean updateEmail(long id, EmailEntity email);
    boolean deleteEmail(long id);
}
