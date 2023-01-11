package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDao dao;

    public GenreService(IGenreDao dao) {
        this.dao = dao;
    }

    @Override
    public List<GenreDTO> get() {
        return dao.get();
    }

    @Override
    public boolean add(String newGenre) {
        return dao.add(newGenre);
    }

    @Override
    public void update(int id, String name) {
        dao.update(id, name);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public boolean exist(int id) {
        if(id <= 0 || id > get().size()){
            throw new IllegalArgumentException("Жанра с таким id не существует в голосовании");
        }
        return this.dao.exist(id);
    }
}
