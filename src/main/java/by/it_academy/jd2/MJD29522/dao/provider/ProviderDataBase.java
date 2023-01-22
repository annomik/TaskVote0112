package by.it_academy.jd2.MJD29522.dao.provider;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.GenreDaoDB;
import by.it_academy.jd2.MJD29522.dao.dataBase.SingerDaoDB;
import by.it_academy.jd2.MJD29522.dao.dataBase.fabrics.GenreDaoDBSingleton;
import by.it_academy.jd2.MJD29522.dao.dataBase.fabrics.SingerDaoDBSingleton;
import by.it_academy.jd2.MJD29522.dao.dataBase.fabrics.VoteDaoDBSingleton;
import by.it_academy.jd2.MJD29522.dao.provider.api.IProviderDao;

import java.beans.PropertyVetoException;

public class ProviderDataBase implements IProviderDao {
    @Override
    public ISingerDao getSingerDao() {
        try {
            return SingerDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IGenreDao getGenreDao() {
        try {
            return GenreDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IVoteDao getIVoteDAo() {
        try {
            return VoteDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
}
