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
        genres.add(new GenreID(new GenreDTO("Classic"), genres.size() + 1));
        genres.add(new GenreID(new GenreDTO("Soul"), genres.size() + 1));
        genres.add(new GenreID(new GenreDTO("Rock"), genres.size() + 1));
        genres.add(new GenreID(new GenreDTO("Folk"), genres.size() + 1));
        genres.add(new GenreID(new GenreDTO("Opera"), genres.size() + 1));
        genres.add(new GenreID(new GenreDTO("Rumba"), genres.size() + 1));
        genres.add(new GenreID(new GenreDTO("Jazz"), genres.size() + 1));
        genres.add(new GenreID(new GenreDTO("Electric"), genres.size() + 1));
        genres.add(new GenreID(new GenreDTO("Blues"), genres.size() + 1));
        genres.add(new GenreID(new GenreDTO("Disco"), genres.size() + 1));
    }
    @Override
    public List<GenreID> get() {
        return genres;
    }

    @Override
    public boolean add(String newGenre) {
        if(!exist(newGenre)){
            genres.add(new GenreID(new GenreDTO(newGenre), genres.size() + 1));
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(int id, String name) {
        genres.add(id - 1, new GenreID(new GenreDTO(name), id));
        return false;
    }

    @Override
    public boolean delete(int id) {
        if(exist(id)){
            genres.remove(id-1);
            return true;
        }
        return false;
    }

    @Override
    public boolean exist(int id) {
        for (GenreID genreID : genres) {
            if(id == genreID.getId()){
                return true;
            }
        }
        return false;
    }

    public boolean exist(String genre) {
        for (GenreID genreID : genres) {
            if (genre.equals(genreID.getGenreDTO().getName())) {
                return true;
            }
        }
        return false;
    }
}
