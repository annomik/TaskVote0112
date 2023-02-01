package by.it_academy.jd2.MJD29522.service.api;

import java.util.List;
import java.util.Map;

public interface IGenreService {

    List<GenreID> get();

    boolean add(String newGenre);

    void update(long id, String name);

    boolean delete(long id);

    boolean exist (long id);

    boolean validation(Map<String, String[]> mapParametrs, String operation);

    String getName(long id);
}
