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
    private boolean sendMessage = true;
    public ServiceSendMail(IMailDao mailDao, Properties properties, boolean sendMessage) {
        this.mailDao = mailDao;
        this.properties = properties;
        this.sendMessage = sendMessage;
    }
    @Override
    public void sendAllMailThroughHour() {
        int count = 0;
        while (sendMessage){
            List<MailID> mails = mailDao.getMessageForSend();
            if(mails!=null){
                for(MailID mail : mails){
                    boolean sending = sendMail(mail);
                    if(sending){
                        mailDao.update(mail.getId(),true,true);
                    } else {
                        count++;
                    }
                    if(count == 5){
                        count = 0;
                        break;
                    }
                }
            }
            try {
                Thread.sleep(7200000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean sendMail(MailID mail){
        boolean send = true;
        Session session = Session.getDefaultInstance(properties);
        try {
            // Создание объекта MimeMessage по умолчанию
            MimeMessage message = new MimeMessage(session);

            // Установить письмо от:
            message.setFrom(new InternetAddress(properties.getProperty("")));

            // Установить письмо Кому:
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getEmail()));
            message.setText(mail.getMassage());
            Transport tr = session.getTransport();
            tr.connect(null, "6NfLpUHWcugYzvcYMQ83" );
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
            mailDao.update(mail.getId(),true,true);
        } catch (MessagingException mex) {
            send = false;
            mex.printStackTrace();
        }
        return send;
    }

    @Override
    public void setSendMassage(boolean sendMassage) {
        this.sendMessage = sendMassage;
    }
}
