package by.it_academy.jd2.MJD29522.dao.provider;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.provider.api.IDaoProvider;

public class ChoiceDaoProvider implements IDaoProvider {
    private boolean useBD = false;

    private static volatile ChoiceDaoProvider instance;
    private IDaoProvider daoProvider;

    private ChoiceDaoProvider() {

        if(useBD){
            this.daoProvider = new DaoDBProvider();
        }else {
            this.daoProvider = new DaoHibernateProvider();
        }
    }

    @Override
    public IGenreDao getGenreDao() {
        return daoProvider.getGenreDao();
    }
    @Override
    public ISingerDao getSingerDao() {
        return daoProvider.getSingerDao();
    }
    @Override
    public IVoteDao getVoteDao() {
        return daoProvider.getVoteDao();
    }

    public static IDaoProvider getInstance(){
        if(instance == null){
            synchronized (ChoiceDaoProvider.class){
                if(instance == null){
                    instance = new ChoiceDaoProvider();
                }
            }
        }return instance;
    }
}
