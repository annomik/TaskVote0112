package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dao.entity.Genre;

import java.util.List;

public interface IGenreDao{
//    List<GenreID> get();

    List<Genre> get();

    boolean add(String newGenre);

    void update(long id, String name);

    boolean delete(long id);

    boolean exist(long id);

    String getName(long id);
}
