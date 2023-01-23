package by.it_academy.jd2.MJD29522.dao.provider.api;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;

public interface IProviderDao {
    ISingerDao getSingerDao();
    IGenreDao getGenreDao();
    IVoteDao getIVoteDAo();
}
