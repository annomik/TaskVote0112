package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dao.Vote;
import by.it_academy.jd2.MJD29522.dto.GenreID;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import by.it_academy.jd2.MJD29522.dto.StatisticDTO;

import java.util.List;


public interface IStatisticService {
     List<StatisticDTO> getResult();
     void setStatistic(Vote vote, GenreID genreID, SingerID singerID);
}
