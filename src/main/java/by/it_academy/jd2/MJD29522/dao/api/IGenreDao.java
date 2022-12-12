package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dto.GenreID;
import java.util.List;

public interface IGenreDao{
    List<GenreID> get();

    boolean exist(int id);
}
