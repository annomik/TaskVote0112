package by.it_academy.jd2.MJD29522;

import by.it_academy.jd2.MJD29522.dao.GenreDao;
import by.it_academy.jd2.MJD29522.dao.SingerDao;
import by.it_academy.jd2.MJD29522.dao.VoteDao;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.GenreService;
import by.it_academy.jd2.MJD29522.service.SingerService;
import by.it_academy.jd2.MJD29522.service.StatisticService;
import by.it_academy.jd2.MJD29522.service.VoteService;
import by.it_academy.jd2.MJD29522.service.fabrics.GenreServiceSingleton;
import by.it_academy.jd2.MJD29522.service.fabrics.SingerServiceSingleton;
import by.it_academy.jd2.MJD29522.service.fabrics.StatisticServiceSingleton;
import by.it_academy.jd2.MJD29522.service.fabrics.VoteServiceSingleton;

public class Main {
    public static void main(String[] args) {
        SingerService singerService = SingerServiceSingleton.getInstance();
        GenreService genreService = GenreServiceSingleton.getInstance();
        VoteService voteService = VoteServiceSingleton.getInstance();
        StatisticService statistic = StatisticServiceSingleton.getInstance();

        VoteDTO voteDTO = new VoteDTO(8, new int[]{2,4,3},"Я дима, заберите меня отсюда");
        voteService.save(voteDTO);


        System.out.println(singerService.get());
        System.out.println(genreService.get());

        System.out.println(statistic.getResultSinger());
        System.out.println(statistic.getResultGenre());
        System.out.println(statistic.getResultMessage());

    }

}
