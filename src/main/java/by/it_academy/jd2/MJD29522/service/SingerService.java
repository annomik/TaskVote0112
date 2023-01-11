package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import java.util.List;

public class SingerService implements ISingerService {

    private final ISingerDao singerDao;

    public SingerService(ISingerDao singerDao) {
        this.singerDao = singerDao;
    }

    @Override
    public List<SingerID> get() {
        return singerDao.get();
    }

    @Override
    public boolean add(String newSinger) {
        return singerDao.add(newSinger);
    }

    @Override
    public void update(int id, String name) { singerDao.update(id, name);
    }

    @Override
    public boolean delete(int id) {
        return singerDao.delete(id);
    }

    @Override
    public boolean exist(int id) {
        return this.singerDao.exist(id);
    }

//    public boolean exist(String name) {
//        return this.singerDao.exist(name);
//    }

}
