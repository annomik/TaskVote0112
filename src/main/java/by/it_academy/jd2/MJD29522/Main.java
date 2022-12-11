package by.it_academy.jd2.MJD29522;

import by.it_academy.jd2.MJD29522.dao.GenreDao;
import by.it_academy.jd2.MJD29522.dao.SingerDao;
import by.it_academy.jd2.MJD29522.dao.VoteDao;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.GenreService;
import by.it_academy.jd2.MJD29522.service.SingerService;
import by.it_academy.jd2.MJD29522.service.VoteService;

public class Main {
    public static void main(String[] args) {
        VoteService voteService = new VoteService(new VoteDao(),
                new SingerService(new SingerDao()),
                new GenreService(new GenreDao()));

        VoteDTO voteDTO = new VoteDTO(1, new int[]{2,4},"Я дима, заберите меня отсюда");

    }

}
