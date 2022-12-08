package by.it_academy.jd2.MJD29522.comparator;

import by.it_academy.jd2.MJD29522.models.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {

        return o2.getDate().compareTo(o1.getDate());
    }
}
