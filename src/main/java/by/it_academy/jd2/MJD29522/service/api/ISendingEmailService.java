package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dto.VoteDTO;

public interface ISendingEmailService {

   void sendEmail(VoteDTO voteDTO);

   boolean validateEmail(String email);

}
