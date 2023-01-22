package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dto.SingerID;

import java.util.List;

public interface ISingerDao {

    List<SingerID> get();

    boolean add(String newSinger);

    void update(long id, String name);

    boolean delete(long id);

    boolean exist(long id);

    String getName(long id);
}
