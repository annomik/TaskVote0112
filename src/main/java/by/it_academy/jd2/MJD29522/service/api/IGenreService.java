package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;


public interface IGenreService {
    void save(GenreDTO genreDTO);

    void validate(GenreDTO genreDTO);
}
