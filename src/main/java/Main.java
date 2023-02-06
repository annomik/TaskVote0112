import by.it_academy.jd2.MJD29522.dao.orm.entity.EmailEntity;
import by.it_academy.jd2.MJD29522.dto.GenreID;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.SendingEmailServiceNew;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.IMailService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;
import by.it_academy.jd2.MJD29522.service.fabrics.*;
import by.it_academy.jd2.MJD29522.dto.Vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String PROTOCOL = "mail.transport.protocol";
    private static final String AUTH = "mail.smtps.auth";
    private static final String HOST = "mail.smtps.host";
    private static final String EMAIL_SENDER = "mail.smtps.user";
    private static final String EMAIL_USER_PASSWORD = "user.password";
//    public static IGenreDao genreDao = ChoiceDaoProvider.getInstance().getGenreDao();
//    public static IMailDao mailDao = ChoiceDaoProvider.getInstance().getMailDao();
//    public static ISingerDao singerDao = ChoiceDaoProvider.getInstance().getSingerDao();
//    public static IVoteDao voteDao = ChoiceDaoProvider.getInstance().getVoteDao();
    public static ISingerService singerService = SingerServiceSingleton.getInstance();
    public static IGenreService genreService = GenreServiceSingleton.getInstance();
    public static IVoteService voteService = VoteServiceSingleton.getInstance();
    public static IMailService mailService = MailServiceSingleton.getInstance();
    public static Properties properties = new Properties();

    public static SendingEmailServiceNew sendingEmail;
    public static void main(String[] args) {
        setProperties();
        sendingEmail = SendingEmailServiceSingletonNew.getInstance(properties);
        sendingEmail.sendEmail();

//        singerService.add("Three");
//        singerService.delete(1);
//        singerService.update(2,"Four");
//        printAllSingers();
//        System.out.println(singerService.getName(2));

        genreService.add("Four");
        genreService.add("Five");
        genreService.update(1,"ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        System.out.println(genreService.getName(2));
        printAllGenres();
//        long[] genresID = {1,2,5};
//        VoteDTO voteDTO = new VoteDTO(3, genresID,"test12","Horvard777@mail.ru", LocalDateTime.now());
//        voteService.save(voteDTO);
//        printVotes();
//        printAllMessages();
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        TestThread testThread = new TestThread();
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                sendingEmail.startEmailSender(15);
//            }
//        });
//        long[] genresID2 = {1,2,5};
//        VoteDTO voteDTO1 = new VoteDTO(3, genresID,"test","Horvard777@mail.ru", LocalDateTime.now());
//        voteService.save(voteDTO1);
    }

    public static void printAllMessages(){
        List<EmailEntity> emails = mailService.getEmailForSend();
        for(EmailEntity email : emails){
            System.out.println(email.toString());
        }
    }

    public static void printVotes(){
        List<Vote> votes = voteService.getVote();
        for(Vote vote : votes){
            System.out.println(vote.toString());
        }
    }

    public static void setProperties(){
        properties.setProperty(PROTOCOL, "smtps");
        properties.setProperty(AUTH,"true");
        properties.setProperty(HOST,"smtp.mail.ru");
        properties.setProperty(EMAIL_SENDER,"");
        properties.setProperty(EMAIL_USER_PASSWORD,"");
    }

    public static void printAllGenres(){
        List<GenreID> genres = genreService.get();
        for(GenreID genre : genres){
            System.out.println(genre.toString());
        }
    }
    public static void printAllSingers(){
        List<SingerID> singers = singerService.get();
        for(SingerID singer : singers){
            System.out.println(singer.toString());
        }
    }

    public static void addNewSinger(){

    }

    public static void addNewVote(){

    }

}
