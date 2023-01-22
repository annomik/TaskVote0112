package by.it_academy.jd2.MJD29522.util;

import by.it_academy.jd2.MJD29522.model.SelectDao;

public class Select {
    private static SelectDao selectDao = SelectDao.MEMORY;

    public static SelectDao getSelectDao(){
        return selectDao;
    }

    public static void setMemoryDataBase(){
        selectDao = SelectDao.MEMORY;
    }
}
