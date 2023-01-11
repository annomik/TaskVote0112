package by.it_academy.jd2.MJD29522;

import by.it_academy.jd2.MJD29522.dao.GenreDao;
import by.it_academy.jd2.MJD29522.dto.GenreID;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenreDao genreDao = new GenreDao();
        List<GenreID> genres = genreDao.get();

        for (GenreID genre : genres) {
            System.out.println(genre.getId() + ": " + genre.getGenreDTO().getName());
        }


        genreDao.add("sadghfgdgfxhgfdcgv");
        genreDao.add("sadghfgdgfxhgfdcgv");
        System.out.println(genreDao.add("sadghfgdgfxhgfdcgv"));

        for (GenreID genre : genres) {
            System.out.println(genre.getId() + ": " + genre.getGenreDTO().getName());
        }

        System.out.println(genreDao.delete(3));

        for (GenreID genre : genres) {
            System.out.println(genre.getId() + ": " + genre.getGenreDTO().getName());
        }

        genreDao.update(11, "Пугачева");

        for (GenreID genre : genres) {
            System.out.println(genre.getId() + ": " + genre.getGenreDTO().getName());
        }
    }
}
