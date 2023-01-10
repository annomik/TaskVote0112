package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dto.GenreID;
import java.util.List;

public interface IGenreService {

    List<GenreID> get();

    boolean add(String newGenre);

    boolean update(int id);

    boolean delete(int id);

    boolean exist (int id);
}
