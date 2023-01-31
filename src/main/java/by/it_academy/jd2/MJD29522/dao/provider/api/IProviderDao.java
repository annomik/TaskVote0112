package by.it_academy.jd2.MJD29522.dao.provider.api;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.mail.api.IMailDao;

public interface IProviderDao {
    ISingerDao getSingerDao();
    IGenreDao getGenreDao();
    IVoteDao getIVoteDAo();
    IMailDao getImailDao();
}
