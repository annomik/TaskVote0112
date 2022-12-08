package by.it_academy.jd2.MJD29522.comparator;

import by.it_academy.jd2.MJD29522.dao.Singer;


import java.util.Comparator;

public class SingerComparator implements Comparator<Singer> {

    @Override
    public int compare(Singer o1, Singer o2) {
        return o2.getCountVote() - o1.getCountVote();
    }
}
