package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dto.GenreID;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import java.util.List;
import java.util.Map;

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
    public boolean add(String newGenre) {
        return dao.add(newGenre);
    }

    @Override
    public void update(long id, String name) {
        dao.update(id, name);
    }

    @Override
    public boolean delete(long id) {
        return dao.delete(id);
    }

    @Override
    public boolean exist(long id) {
        return this.dao.exist(id);
    }

    public boolean validation(Map<String, String[]> mapParametrs, String paramert) {

        List<GenreID> genres = get();

        String[] paramerts = mapParametrs.get(paramert);

        if (paramerts.length > 1) {
            throw new IllegalArgumentException("Выполнять операции с жанрами можно только по одному. Введите только " +
                    "один жанр");
        }
        if (paramerts[0] == null || paramerts[0].isBlank()) {
            throw new IllegalArgumentException("Не передан параметр жанра для выполнения операции");
        }

        String nameForAdd = paramerts[0];
        for (GenreID genre : genres) {
            if (nameForAdd.equals(genre.getGenreDTO().getName())) {
                throw new IllegalArgumentException("Жанр с именем " + nameForAdd + " уже есть в списке жанров");
            }
        }
        return true;
    }

    @Override
    public String getName(long id) {
        return dao.getName(id);
    }
}
