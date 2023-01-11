package by.it_academy.jd2.MJD29522;

import by.it_academy.jd2.MJD29522.dao.GenreDao;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenreDao genreDao = new GenreDao();
        List<GenreDTO> genres = genreDao.get();

        for (GenreDTO genre : genres) {
            System.out.println(genreDao.getId(genre.getName()) + ": " + genre.getName());
        }

        System.out.println(genreDao.getId("Folk") + ": Folk");

        genreDao.add("sadghfgdgfxhgfdcgv");

        for (GenreDTO genre : genres) {
            System.out.println(genreDao.getId(genre.getName()) + ": " + genre.getName());
        }

        System.out.println(genreDao.delete(3));

        for (GenreDTO genre : genres) {
            System.out.println(genreDao.getId(genre.getName()) + ": " + genre.getName());
        }

        genreDao.update(10, "Пугачева");

        for (GenreDTO genre : genres) {
            System.out.println(genreDao.getId(genre.getName()) + ": " + genre.getName());
        }
        System.out.println(genreDao.getId("Folk") + ": Folk");
    }
}
