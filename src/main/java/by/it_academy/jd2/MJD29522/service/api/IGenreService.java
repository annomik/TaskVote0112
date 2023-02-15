package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.dto.GenreDTOWithID;
import by.it_academy.jd2.MJD29522.entity.GenreEntity;

import java.util.List;
import java.util.Map;

public interface IGenreService {

    List<GenreDTOWithID> get();

    boolean add(GenreDTO newGenre);

    void update(long id, long version, GenreDTO name);

    boolean delete(long id, long version);

    boolean exist (long id);

    boolean validation(GenreDTO genre);

    String getName(long id);
    GenreDTOWithID getCard(long id);

}
