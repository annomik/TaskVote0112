package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dto.*;

import java.util.List;


public interface IStatisticService {
     List<StatisticDTOArtistOrGenre> getResultArtist();
     List<StatisticDTOArtistOrGenre> getResultGenre();
     List<StatisticDTOMessage> getResultMessage();
     void setStatistic(Vote vote, GenreID genreID, SingerID singerID);
}
