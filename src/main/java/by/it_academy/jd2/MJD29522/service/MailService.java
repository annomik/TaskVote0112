package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.IMailDao;
import by.it_academy.jd2.MJD29522.entity.EmailEntity;
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
    public void save(VoteDTO voteDTO) {
        EmailEntity email = new EmailEntity();
        email.setMessage(massageText(voteDTO));
        email.setValidateEmail(validateEmail(voteDTO.getEmail()));
        email.setSendMassage(true);
        email.setLastSendTime(System.currentTimeMillis());
        email.setEmail(voteDTO.getEmail());
        mailDao.addEmail(email);
    }

    @Override
    public boolean update(long id, String message, boolean validateEmail,
                          boolean sendMassage, long lastSendTime, String email) {
        boolean isUpdate;
        isUpdate = mailDao.updateEmail(id, message,validateEmail(email),sendMassage,
                System.currentTimeMillis(),email);
        return isUpdate;
    }

    @Override
    public List<EmailEntity> getEmailForSend() {
        List<EmailEntity> emails = mailDao.getEmailsForSend();
        return emails;
    }

    private boolean validateEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    private String massageText(VoteDTO voteDTO){
        StringBuilder message = new StringBuilder();
        message.append("Голос за артиста:\n        " + singerService.getName(voteDTO.getSingerID()) +".\n");
        message.append("Голос за жанры:\n");
        for(long id : voteDTO.getGenresID()){
            message.append("        " + genreService.getName(id) + ".\n");
        }
        message.append("Ваше сообщение о себе:\n        " + voteDTO.getMessage() + ".\n");
        message.append("Вы указали email:\n        " + voteDTO.getEmail() + ".\n");
        message.append("Голос оставлен:\n        " + voteDTO.getLocalDate() + ".\n");
        return message.toString();
    }
}
