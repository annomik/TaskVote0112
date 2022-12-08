package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dao.Singer;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;

import java.util.List;

public interface ISingerDao {

    void save(SingerDTO singerDTO);

    public List<String> getSingerList();

}
