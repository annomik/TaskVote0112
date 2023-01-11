package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;

import java.util.ArrayList;
import java.util.List;

public class GenreDao implements IGenreDao {
    private List<GenreDTO> genres = new ArrayList<>();
    public GenreDao(List<GenreDTO> genres) {
        this.genres = genres;
    }
    public GenreDao() {
        genres.add(new GenreDTO("Classic"));
        genres.add(new GenreDTO("Soul"));
        genres.add(new GenreDTO("Rock"));
        genres.add(new GenreDTO("Folk"));
        genres.add(new GenreDTO("Opera"));
        genres.add(new GenreDTO("Rumba"));
        genres.add(new GenreDTO("Jazz"));
        genres.add(new GenreDTO("Electric"));
        genres.add(new GenreDTO("Blues"));
        genres.add(new GenreDTO("Disco"));
    }
    @Override
    public List<GenreDTO> get() {
        return genres;
    }

    @Override
    public boolean add(String newGenre) {
        if(!exist(newGenre)){
            genres.add(new GenreDTO(newGenre));
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void update(int id, String name) {
        genres.set(id - 1, new GenreDTO(name));
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
            if(genres.size() >= id && id > 0){
                return true;
            } else {
                return false;
            }
    }

    public boolean exist(String genre) {
        for (GenreDTO genreDTO : genres) {
            if (genre.equals(genreDTO.getName())) {
                return true;
            }
        }
        return false;
    }

    public int getId(String genre){
        int id = -1;
        for (GenreDTO genreDTO : genres) {
            if(genreDTO.getName().equals(genre)){
                id = genres.indexOf(genreDTO) + 1;
            }
        }
        return id;
    }
}
