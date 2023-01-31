package by.it_academy.jd2.MJD29522.mail;

import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.mail.api.IMailDao;
import by.it_academy.jd2.MJD29522.mail.api.IMailService;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;

import java.time.LocalDate;
import java.util.List;

public class ServiceMail implements IMailService {

    private IMailDao mailDao;
    private IGenreService genreService;
    private ISingerService singerService;
    public ServiceMail(IMailDao mailDao, ISingerService singerService,
                       IGenreService genreService) {
        this.mailDao = mailDao;
        this.genreService = genreService;
        this.singerService = singerService;
    }

    @Override
    public List<MailID> getAllMessage() {
        List<MailID> mails = mailDao.getAllMessage();
        return mails;
    }

    @Override
    public List<MailID> getMessageForSend() {
        List<MailID> mails = mailDao.getMessageForSend();
        return mails;
    }

    @Override
    public boolean save(Vote vote) {
        StringBuilder message = new StringBuilder();
        message.append("Вы проголосовали за "+genreService.getName(vote.getVoteDTO().getSingerID())+" ариста.\n");
        message.append("Голоса за жанры:\n");
        for(long id : vote.getVoteDTO().getGenresID()){
            message.append(genreService.getName(id)+".\n");
        }
        message.append("Время голосования: "+vote.getVoteDTO().getLocalDate()+"\n");
        message.append("Почта пользователя: "+vote.getVoteDTO().getEmail()+"\n");
        MailDTO mailDTO = new MailDTO(message.toString(),vote.getVoteDTO().getEmail(),
                vote.getVoteDTO().getLocalDate(),false,true,
                LocalDate.of(1980,01,01));
        return mailDao.save(mailDTO);
    }
}
