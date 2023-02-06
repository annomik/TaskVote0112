package by.it_academy.jd2.MJD29522.dao.provider;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.IMailDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.provider.api.IDaoProvider;

public class DaoDBProvider implements IDaoProvider {
    @Override
    public IGenreDao getGenreDao() {
        return null;
    }
    @Override
    public ISingerDao getSingerDao() {
        return null;
    }

    @Override
    public IVoteDao getVoteDao() {
        return null;
    }

    @Override
    public IMailDao getMailDao() {
        return null;
    }
}
