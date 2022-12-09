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
    public boolean exist(String name) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Имя исполнителя не может быть пустым!");
        }

        List<SingerID> singerIDS = this.singerDao.get();
        for (SingerID singerID: singerIDS){
            if(name.equals(singerID.getSingerDTO().getName())){
                return true;
            }
        }

        return false;
    }
}
