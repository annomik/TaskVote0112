package by.it_academy.jd2.MJD29522.service.api;

public interface ISendingEmailService {

   void sendEmail(String emailReceiver);

   boolean validateEmail(String email);

}
