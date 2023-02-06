package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.entity.EmailEntity;

import java.util.List;

public interface IMailDao {
    List<EmailEntity> getEmails();
    List<EmailEntity> getEmailsForSend();
    boolean addEmail(EmailEntity email);
    boolean updateEmail(long id, String message, boolean validateEmail,
                        boolean sendMassage, long lastSendTime, String email);

    boolean deleteEmail(long id);

    EmailEntity getEmail(long id);
}
