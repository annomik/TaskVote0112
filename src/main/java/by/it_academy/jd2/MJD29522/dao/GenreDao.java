package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;

import java.util.ArrayList;
import java.util.List;

public class GenreDao implements IGenreDao {

    private List<String> genres = new ArrayList<>();

    public GenreDao() {

    }

    @Override
    public List <String> getGenreList() {
        return genres;
    }


//     genres.add("Classic");
//      genres.add("Soul");
//      genres.add("Rock");
//         genres.add("Folk");
//         genres.add("Opera");
//         genres.add("Rumba");
//         genres.add("Jazz"));
//       genres.add(new GenreDao("Electric"));
//        genres.add(new GenreDao("Blues"));
//         genres.add(new GenreDao("Disco"));



}
