package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dao.orm.entity.GenreEntity;
import java.util.List;

public interface IGenreDao{
//    List<GenreID> get();

    List<GenreEntity> get();

    boolean add(String newGenre);

    void update(GenreEntity genreEntity);

    boolean delete(long id);

    boolean exist(long id);

    String getName(long id);
}
