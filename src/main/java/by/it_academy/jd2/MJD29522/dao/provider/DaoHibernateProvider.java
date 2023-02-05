package by.it_academy.jd2.MJD29522.dao.provider;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.IMailDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.orm.fabrics.EmailDaoHibernateSingleton;
import by.it_academy.jd2.MJD29522.dao.orm.fabrics.GenreDaoHibernateSingleton;
import by.it_academy.jd2.MJD29522.dao.orm.fabrics.SingerDaoHibernateSingleton;
import by.it_academy.jd2.MJD29522.dao.orm.fabrics.VoteDaoHibernateSingleton;
import by.it_academy.jd2.MJD29522.dao.provider.api.IDaoProvider;

public class DaoHibernateProvider implements IDaoProvider {

    @Override
    public IGenreDao getGenreDao() {
        return GenreDaoHibernateSingleton.getInstance();
    }
    @Override
    public ISingerDao getSingerDao() {
        return SingerDaoHibernateSingleton.getInstance();
    }
    @Override
    public IVoteDao getVoteDao() {
        return VoteDaoHibernateSingleton.getInstance();
    }

    @Override
    public IMailDao getMailDao() {
        return EmailDaoHibernateSingleton.getInstance();
    }
}
