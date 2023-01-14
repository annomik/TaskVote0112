package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dto.SingerID;

import java.util.List;

public class SingerDaoDB implements ISingerDao {
    @Override
    public List<SingerID> get() {
        return null;
    }

    @Override
    public boolean add(String newSinger) {
        return false;
    }

    @Override
    public void update(int id, String name) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean exist(int id) {
        return false;
    }
}
