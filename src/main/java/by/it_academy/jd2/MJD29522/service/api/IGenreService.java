package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import java.util.List;

public interface IGenreService {

    List<GenreDTO> get();

    boolean add(String newGenre);

    void update(int id, String name);

    boolean delete(int id);

    boolean exist (int id);
}
