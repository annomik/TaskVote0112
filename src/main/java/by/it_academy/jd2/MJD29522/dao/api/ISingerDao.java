package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dto.SingerID;

import java.util.List;

public interface ISingerDao {

    List<SingerID> get();

    boolean add(String newSinger);

    void update(int id, String name);

    boolean delete(int id);

    boolean exist(int id);
}
