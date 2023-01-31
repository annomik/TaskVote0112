package by.it_academy.jd2.MJD29522.mail;

import by.it_academy.jd2.MJD29522.mail.api.IMailDao;
import by.it_academy.jd2.MJD29522.mail.api.IServiceSendMail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class ServiceSendMail implements IServiceSendMail {
    private IMailDao mailDao;
    private Properties properties;
    public ServiceSendMail(IMailDao mailDao, Properties properties) {
        this.mailDao = mailDao;
        this.properties = properties;
    }

    @Override
    public boolean send() {
        List<MailID> mails = mailDao.getMessageForSend();
        Session session = Session.getDefaultInstance(properties);

        try {
            // Создание объекта MimeMessage по умолчанию
            MimeMessage message = new MimeMessage(session);

            // Установить письмо от:
            message.setFrom(new InternetAddress(EMAIL_SENDER));

            // Установить письмо Кому:
            for(MailID mail : mails){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getEmail()));
                message.setText(mail.getMassage());
                Transport tr = session.getTransport();
                tr.connect(null, "6NfLpUHWcugYzvcYMQ83" );
                tr.sendMessage(message, message.getAllRecipients());
            }
            tr.close();
            System.out.println("Сообщение успешно отправлено!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return false;
    }

}
