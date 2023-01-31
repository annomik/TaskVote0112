package by.it_academy.jd2.MJD29522.mail.api;

import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.mail.MailDTO;
import by.it_academy.jd2.MJD29522.mail.MailID;

import java.util.List;

public interface IMailService {
    List<MailID> getAllMessage();
    List<MailID> getMessageForSend();
    boolean save(Vote vote);
}
