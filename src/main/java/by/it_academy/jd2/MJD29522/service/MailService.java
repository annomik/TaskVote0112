package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.IMailDao;
import by.it_academy.jd2.MJD29522.dao.orm.entity.EmailEntity;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.IMailService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailService implements IMailService {

    private static final String EMAIL_REGEX =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private final ISingerService singerService;
    private final IGenreService genreService;
    private final IMailDao mailDao;

    public MailService(ISingerService singerService, IGenreService genreService, IMailDao mailDao) {
        this.singerService = singerService;
        this.genreService = genreService;
        this.mailDao = mailDao;
    }

    @Override
    public boolean save(VoteDTO voteDTO) {
        boolean isSave = false;
        EmailEntity email = new EmailEntity();
        email.setMessage(massageText(voteDTO));
        email.setValidateEmail(validateEmail(voteDTO.getEmail()));
        email.setSendMassage(true);
        email.setLastSendTime(System.currentTimeMillis());
        email.setEmail(voteDTO.getEmail());
        isSave = mailDao.addEmail(email);
        return isSave;
    }

    @Override
    public boolean update(long id, String message, boolean validateEmail,
                          boolean sendMassage, long lastSendTime, String email) {
        boolean isUpdate = false;
        isUpdate = mailDao.updateEmail(id, message,validateEmail(email),sendMassage,
                System.currentTimeMillis(),email);
        return isUpdate;
    }

    @Override
    public List<EmailEntity> getEmailForSend() {
        List<EmailEntity> emails = mailDao.getEmailsForSend();
        return emails;
    }

    @Override
    public void send() {

    }

    private boolean validateEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    private String massageText(VoteDTO voteDTO){
        StringBuilder message = new StringBuilder();
        message.append("Голос за артиста: "+singerService.getName(voteDTO.getSingerID())+", ");
        message.append("Голоса за жанры: ");
        for(long id : voteDTO.getGenresID()){
            message.append(genreService.getName(id)+", ");
        }
        message.append("о себе: "+voteDTO.getMessage()+", ");
        message.append("Ваше сообщение: "+voteDTO.getMessage());
        message.append("Ваша почта: "+voteDTO.getEmail());
        message.append("В "+voteDTO.getLocalDate());
        return message.toString();
    }
}
