package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dto.GenreID;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDao dao;

    public GenreService(IGenreDao dao) {
        this.dao = dao;
    }

    @Override
    public List<GenreID> get() {
        return dao.get();
    }

    @Override
    public boolean exist(int id) {
        if(id == 0){
            throw new IllegalArgumentException("ID жанра не может быть 0!");
        }
        return this.dao.exist(id);
    }
}
