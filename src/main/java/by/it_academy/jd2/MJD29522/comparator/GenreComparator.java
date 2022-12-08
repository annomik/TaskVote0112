package by.it_academy.jd2.MJD29522.comparator;

import by.it_academy.jd2.MJD29522.dao.Genre;


import java.util.Comparator;

public class GenreComparator implements Comparator<Genre> {

    @Override
    public int compare(Genre o1, Genre o2) {

        return o2.getCountVote() - o1.getCountVote();
    }
}
