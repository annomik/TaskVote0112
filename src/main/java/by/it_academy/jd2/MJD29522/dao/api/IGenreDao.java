package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dto.GenreID;
import java.sql.SQLException;
import java.util.List;

public interface IGenreDao{
//    List<GenreID> get();

    List<GenreID> get() throws SQLException;

    boolean add(String newGenre);

    void update(long id, String name);

    boolean delete(long id);

    boolean exist(long id);
}
