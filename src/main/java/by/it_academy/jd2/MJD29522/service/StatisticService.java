package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.StatisticDTOArtistOrGenre;
import by.it_academy.jd2.MJD29522.dto.StatisticDTOMessage;
import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.api.IStatisticService;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;

import java.util.List;

public class StatisticService implements IStatisticService {

    private final IVoteDao dao;
    private final ISingerService singerService;
    private final IGenreService genreService;

    public StatisticService(IVoteDao dao, ISingerService singerService, IGenreService genreService) {
        this.dao = dao;
        this.singerService = singerService;
        this.genreService = genreService;
    }


    @Override
    public List<StatisticDTOArtistOrGenre> getResultGenre() {
        return null;
    }

    @Override
    public List<StatisticDTOArtistOrGenre> getResultArtist() {
        return null;
    }

    @Override
    public List<StatisticDTOMessage> getResultMessage() {
        return null;
    }
}
