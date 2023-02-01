package by.it_academy.jd2.MJD29522.mail.api;

public interface IServiceSendMail {

    void sendAllMailThroughHour();
    boolean sendMail(MailID mail);

    void setSendMassage(boolean sendMassage);

}
