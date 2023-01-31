package by.it_academy.jd2.MJD29522.dao.provider;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.provider.api.IProviderDao;
import by.it_academy.jd2.MJD29522.mail.api.IMailDao;
import by.it_academy.jd2.MJD29522.util.Select;

public class SelectedDaoProvider implements IProviderDao {
    private volatile static SelectedDaoProvider instance;
    private IProviderDao providerDao;

    private SelectedDaoProvider() {
        switch (Select.getSelectDao()){
            case DATABASE:
                providerDao = new ProviderDataBase();
                break;
            default:
                providerDao = new ProviderMemory();
                break;
        }
    }

    @Override
    public ISingerDao getSingerDao() {
        return providerDao.getSingerDao();
    }

    @Override
    public IGenreDao getGenreDao() {
        return providerDao.getGenreDao();
    }

    @Override
    public IVoteDao getIVoteDAo() {
        return providerDao.getIVoteDAo();
    }

    @Override
    public IMailDao getImailDao() {return providerDao.getImailDao();}

    public static SelectedDaoProvider getInstance() {
        if(instance==null){
            synchronized (SelectedDaoProvider.class){
                if(instance==null){
                    instance = new SelectedDaoProvider();
                }
            }
        }
        return instance;
    }
}
