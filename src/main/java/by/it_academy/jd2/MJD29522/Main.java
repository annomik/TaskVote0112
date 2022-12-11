package by.it_academy.jd2.MJD29522;

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

        System.out.println(singerService.get());
        System.out.println(genreService.get());

        VoteDTO voteDTO = new VoteDTO(1, new int[]{1,2,7},"Дима, заберите меня отсюда");
        VoteDTO voteDTO2 = new VoteDTO(2, new int[]{1,5,4},"Дима, заберите меня отсюда");
        VoteDTO voteDTO3 = new VoteDTO(1, new int[]{1,3,6},"Дима, заберите меня отсюда");


        voteService.save(voteDTO);
        voteService.save(voteDTO2);
        voteService.save(voteDTO3);

        System.out.println(statistic.getResultSinger());
        System.out.println(statistic.getResultGenre());
        System.out.println(statistic.getResultMessage());
    }
}
