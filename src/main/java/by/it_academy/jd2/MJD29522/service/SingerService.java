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
    public boolean exist(int id) {
        if (id == 0){
            throw new IllegalArgumentException("ID исполнителя не может быть 0!");
        }

        List<SingerID> singerIDS = this.singerDao.get();
        for (SingerID singerID: singerIDS){
                return this.singerDao.exist(id);
        }
        return false;
    }
}
