package by.it_academy.jd2.MJD29522.dao.provider;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.fabrics.VoteDaoDBSingleton;

import by.it_academy.jd2.MJD29522.dao.orm.fabrics.GenreHibernateSingleton;
import by.it_academy.jd2.MJD29522.dao.orm.fabrics.SingerHibernateSingleton;
import by.it_academy.jd2.MJD29522.dao.provider.api.IDaoProvider;

import java.beans.PropertyVetoException;

public class DaoHibernateProvider implements IDaoProvider {
    @Override
    public IGenreDao getGenreDao() {
            return GenreHibernateSingleton.getInstance();
    }

    @Override
    public ISingerDao getSingerDao() {
            return SingerHibernateSingleton.getInstance();
    }

    @Override
    public IVoteDao getVoteDao() {
        try {
            return VoteDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new IllegalStateException(e);
        }
    }
}
