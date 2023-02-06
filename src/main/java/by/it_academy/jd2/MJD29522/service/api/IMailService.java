package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.entity.EmailEntity;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;

import java.util.List;

public interface IMailService {

    void save(VoteDTO voteDTO);
    boolean update(long id, String message, boolean validateEmail,
                   boolean sendMassage, long lastSendTime, String email);
    List<EmailEntity> getEmailForSend();

}
