package by.it_academy.jd2.MJD29522.mail.api;

import by.it_academy.jd2.MJD29522.dto.Vote;

import java.util.List;

public interface IMailService {
    List<MailID> getAllMessage();
    List<MailID> getMessageForSend();
    boolean save(Vote vote);
}
