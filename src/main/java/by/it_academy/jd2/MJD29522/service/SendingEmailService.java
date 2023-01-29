package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.ISendingEmailService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendingEmailService implements ISendingEmailService {

    private static final String PROTOCOL = "mail.transport.protocol";
    private static final String AUTH = "mail.smtps.auth";
    private static final String HOST = "mail.smtps.host";
    private static final String EMAIL_SENDER = "mail.smtps.user";
    private static final String EMAIL_USER_PASSWORD = "user.password";
    private static final String EMAIL_REGEX =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                                 "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private Properties properties = new Properties();
    IGenreService genreService;
    ISingerService singerService;

    public SendingEmailService(Properties prop, ISingerService singerService, IGenreService genreService) {
        this.genreService = genreService;
        this.singerService = singerService;
        this.properties.setProperty(PROTOCOL, prop.getProperty(PROTOCOL));
        this.properties.setProperty(AUTH, prop.getProperty(AUTH));
        this.properties.setProperty(HOST, prop.getProperty(HOST));
        this.properties.setProperty(EMAIL_SENDER, prop.getProperty(EMAIL_SENDER));
        this.properties.setProperty(EMAIL_USER_PASSWORD, prop.getProperty(EMAIL_USER_PASSWORD));
    }

    @Override
    public void sendEmail(VoteDTO voteDTO) {
        if (validateEmail(voteDTO.getEmail())) {
            System.out.println("The email address " + voteDTO.getEmail() + " is valid");
        } else {
            throw new IllegalArgumentException("Некорректный email. Проверьте правильность введенных данных");
        }
        // Получение объекта Session по умолчанию
        Session session = Session.getDefaultInstance(properties);
        try {
            // Создание объекта MimeMessage по умолчанию
            MimeMessage message = new MimeMessage(session);

            // Установить письмо от:
            message.setFrom(new InternetAddress(properties.getProperty(EMAIL_SENDER)));

            // Установить письмо Кому:
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(voteDTO.getEmail()));

            StringBuilder stringBuilder = new StringBuilder();
            // Установить тему:
            message.setSubject("Ваш голос учтен. Спасибо за ваш голос!");
            //message.setContent("<h1>Это актуальное сообщение</h1>", "text/html");
            stringBuilder.append("Вы проголосовали за исполнителя: " + singerService.getName(voteDTO.getSingerID()) + "\n");
            for(long id : voteDTO.getGenresID()) {
                stringBuilder.append("Вы проголосовали за жанр: " + genreService.getName(id) + "\n");
            }

            stringBuilder.append("Вы оставили о себе следующее сообщение: " + voteDTO.getMessage() + ".\n");
            message.setText(stringBuilder.toString());

            // Отправить сообщение
            Transport tr = session.getTransport();
            tr.connect(null, properties.getProperty(EMAIL_USER_PASSWORD) );
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();

            System.out.println("Сообщение успешно отправлено!");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

   @Override
    public boolean validateEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
