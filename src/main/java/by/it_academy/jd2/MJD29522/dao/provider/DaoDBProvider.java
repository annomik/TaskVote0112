package by.it_academy.jd2.MJD29522.dao.provider;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.fabrics.GenreDaoDBSingleton;
import by.it_academy.jd2.MJD29522.dao.dataBase.fabrics.SingerDaoDBSingleton;
import by.it_academy.jd2.MJD29522.dao.dataBase.fabrics.VoteDaoDBSingleton;
import by.it_academy.jd2.MJD29522.dao.memory.fabrics.SingerDaoMemorySingleton;
import by.it_academy.jd2.MJD29522.dao.memory.fabrics.VoteDaoMemorySingleton;
import by.it_academy.jd2.MJD29522.dao.provider.api.IDaoProvider;

import java.beans.PropertyVetoException;

public class DaoDBProvider implements IDaoProvider {
    @Override
    public IGenreDao getGenreDao() {
        try {
            return GenreDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new IllegalStateException(e);
        }
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
