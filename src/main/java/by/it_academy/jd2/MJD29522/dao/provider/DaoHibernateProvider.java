package by.it_academy.jd2.MJD29522.dao.provider;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.fabrics.SingerDaoDBSingleton;
import by.it_academy.jd2.MJD29522.dao.dataBase.fabrics.VoteDaoDBSingleton;
import by.it_academy.jd2.MJD29522.dao.orm.fabrics.GenreDaoHibernateSingleton;
import by.it_academy.jd2.MJD29522.dao.provider.api.IDaoProvider;

import java.beans.PropertyVetoException;

public class DaoHibernateProvider implements IDaoProvider {
    @Override
    public IGenreDao getGenreDao() {
        return GenreDaoHibernateSingleton.getInstance();
    }
    @Override
    public ISingerDao getSingerDao() {
        try {
            return SingerDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new IllegalStateException(e);
        }
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
