package by.it_academy.jd2.MJD29522.dao.memory;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.dto.GenreID;

import java.util.ArrayList;
import java.util.List;

public class GenreDaoMemory implements IGenreDao {
    private List<GenreID> genres = new ArrayList<>();

    private volatile long id = 0;
    public GenreDaoMemory(List<GenreID> genres) {
        this.genres = genres;
    }
    public GenreDaoMemory() {
        genres.add(new GenreID(new GenreDTO("Classic"), generateId()));
        genres.add(new GenreID(new GenreDTO("Soul"), generateId()));
        genres.add(new GenreID(new GenreDTO("Rock"), generateId()));
        genres.add(new GenreID(new GenreDTO("Folk"), generateId()));
        genres.add(new GenreID(new GenreDTO("Opera"), generateId()));
        genres.add(new GenreID(new GenreDTO("Rumba"), generateId()));
        genres.add(new GenreID(new GenreDTO("Jazz"), generateId()));
        genres.add(new GenreID(new GenreDTO("Electric"), generateId()));
        genres.add(new GenreID(new GenreDTO("Blues"), generateId()));
        genres.add(new GenreID(new GenreDTO("Disco"), generateId()));
    }
    public long generateId(){
        synchronized (GenreDaoMemory.class){
            id++;
        }
        return  id;
    }
    @Override
    public synchronized List<GenreID> get() {
        return genres;
    }
    @Override
    public boolean add(String newGenre) {
        long newId = generateId();
        if(!exist(newGenre)){
            while (exist(newId)){
                newId = generateId();
            }
            genres.add(new GenreID(new GenreDTO(newGenre), newId));
            return true;
        }else{
            return false;
        }
    }
    @Override
    public synchronized void update(long id, String name) {
        for (GenreID genre : genres) {
            if(genre.getId() == id){
                genres.set(genres.indexOf(genre), new GenreID(new GenreDTO(name), id));
            }
        }
    }
    @Override
    public synchronized boolean delete(long id) {
        for (GenreID genre : genres) {
            if(genre.getId() == id){
                genres.remove(genre);
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized boolean exist(long id) {
        for (GenreID genreID : genres) {
            if(id == genreID.getId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName(long id) {
        for(GenreID genreID : genres){
            if(genreID.getId() == id){
                return genreID.getGenreDTO().getName();
            }
        }
        return null;
    }

    public synchronized boolean exist(String genre) {
        for (GenreID genreID : genres) {
            if (genre.equals(genreID.getGenreDTO().getName())) {
                return true;
            }
        }
        return false;
    }
}
