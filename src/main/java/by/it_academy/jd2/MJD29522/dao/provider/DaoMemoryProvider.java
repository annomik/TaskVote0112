package by.it_academy.jd2.MJD29522.dao.provider;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.memory.fabrics.GenreDaoMemorySingleton;
import by.it_academy.jd2.MJD29522.dao.memory.fabrics.SingerDaoMemorySingleton;
import by.it_academy.jd2.MJD29522.dao.memory.fabrics.VoteDaoMemorySingleton;
import by.it_academy.jd2.MJD29522.dao.provider.api.IDaoProvider;

public class DaoMemoryProvider implements IDaoProvider {
    @Override
    public IGenreDao getGenreDao() {
        return GenreDaoMemorySingleton.getInstance();
    }
    @Override
    public ISingerDao getSingerDao() {
        return SingerDaoMemorySingleton.getInstance();
    }
    @Override
    public IVoteDao getVoteDao() {
        return VoteDaoMemorySingleton.getInstance();
    }
}
