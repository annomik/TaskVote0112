package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.orm.entity.EmailEntity;
import by.it_academy.jd2.MJD29522.service.api.IMailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class SendingEmailServiceNew{

    private static final String PROTOCOL = "mail.transport.protocol";
    private static final String AUTH = "mail.smtps.auth";
    private static final String HOST = "mail.smtps.host";
    private static final String EMAIL_SENDER = "mail.smtps.user";
    private static final String EMAIL_USER_PASSWORD = "user.password";
    private final Properties properties = new Properties();

    private final IMailService mailService;

    public SendingEmailServiceNew(Properties prop, IMailService mailService) {
        this.properties.setProperty(PROTOCOL, prop.getProperty(PROTOCOL));
        this.properties.setProperty(AUTH, prop.getProperty(AUTH));
        this.properties.setProperty(HOST, prop.getProperty(HOST));
        this.properties.setProperty(EMAIL_SENDER, prop.getProperty(EMAIL_SENDER));
        this.properties.setProperty(EMAIL_USER_PASSWORD, prop.getProperty(EMAIL_USER_PASSWORD));
        this.mailService = mailService;
    }
    
    public void sendEmail() {
        List<EmailEntity> emails = mailService.getEmailForSend();
        if(emails==null){
            return;
        }
        if(emails.size()==0){
            return;
        }
        for(EmailEntity email : emails) {
            // Получение объекта Session по умолчанию
            Session session = Session.getDefaultInstance(properties);

            try {
                // Создание объекта MimeMessage по умолчанию
                MimeMessage message = new MimeMessage(session);

                // Установить письмо от:
                message.setFrom(new InternetAddress(properties.getProperty(EMAIL_SENDER)));

                // Установить письмо Кому:
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmail()));

                // Установить тему:
                message.setSubject("Ваш голос учтен. Спасибо за ваш голос!");
                //message.setContent("<h1>Это актуальное сообщение</h1>", "text/html");

                message.setText(email.getMessage());

                // Отправить сообщение
                Transport tr = session.getTransport();
                tr.connect(null, properties.getProperty(EMAIL_USER_PASSWORD));
                tr.sendMessage(message, message.getAllRecipients());
                tr.close();

                System.out.println("Сообщение успешно отправлено!");
                mailService.update(email.getId(), email.getMessage(),email.isValidateEmail(),true,
                        System.currentTimeMillis(), email.getEmail());
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }

}
