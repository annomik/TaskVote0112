package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.dto.GenreID;
import java.util.List;

public interface IGenreDao{
//    List<GenreID> get();

    List<GenreDTO> get();

    boolean add(String newGenre);

    void update(int id, String name);

    boolean delete(int id);

    boolean exist(int id);
}
