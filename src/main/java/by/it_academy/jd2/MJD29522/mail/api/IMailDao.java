package by.it_academy.jd2.MJD29522.mail.api;

import by.it_academy.jd2.MJD29522.mail.MailDTO;
import by.it_academy.jd2.MJD29522.mail.MailID;

import java.util.List;

public interface IMailDao {
    List<MailID> getAllMessage();
    List<MailID> getMessageForSend();

    boolean update(long id, boolean statusSend, boolean statusEmail);
    boolean save(MailDTO mailDTO);
}
