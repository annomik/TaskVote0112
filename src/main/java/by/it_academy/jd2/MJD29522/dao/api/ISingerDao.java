package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dto.SingerID;
import java.util.List;

public interface ISingerDao {

    public List<SingerID> get();

    boolean exist(int id);

}
