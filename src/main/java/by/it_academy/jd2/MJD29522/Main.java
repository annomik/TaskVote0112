package by.it_academy.jd2.MJD29522;

import by.it_academy.jd2.MJD29522.dao.dataBase.GenreDaoDB;
import by.it_academy.jd2.MJD29522.dto.GenreID;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenreDaoDB genreDaoDB = new GenreDaoDB();

        List<GenreID> genres = genreDaoDB.get();

        for (GenreID genre : genres) {
            System.out.println(genre.getId() + ": " + genre.getGenreDTO().getName());
        }
    }
}
