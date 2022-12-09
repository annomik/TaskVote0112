package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.dto.GenreID;

import java.util.ArrayList;
import java.util.List;

public class GenreDao implements IGenreDao {
    private List<GenreID> genres = new ArrayList<>();
    public GenreDao(List<GenreID> genres) {
        this.genres = genres;
    }
    public GenreDao() {
        genres.add(new GenreID(new GenreDTO("Classic"), 1));
        genres.add(new GenreID(new GenreDTO("Soul"), 2));
        genres.add(new GenreID(new GenreDTO("Rock"), 3));
        genres.add(new GenreID(new GenreDTO("Folk"), 4));
        genres.add(new GenreID(new GenreDTO("Opera"), 5));
        genres.add(new GenreID(new GenreDTO("Rumba"), 6));
        genres.add(new GenreID(new GenreDTO("Jazz"), 7));
        genres.add(new GenreID(new GenreDTO("Electric"), 8));
        genres.add(new GenreID(new GenreDTO("Blues"), 9));
        genres.add(new GenreID(new GenreDTO("Disco"), 10));
    }
    @Override
    public List<GenreID> get() {
        return genres;
    }

    @Override
    public boolean exist(int id) {
        List<GenreID> genreIDs = genres;
        for (GenreID genreID : genreIDs) {
            if(id == genreID.getId()){
                return true;
            }
        }
        return false;
    }
}
