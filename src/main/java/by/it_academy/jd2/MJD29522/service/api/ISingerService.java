package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dto.SingerID;
import java.util.List;

public interface ISingerService {

   List<SingerID> get();

   boolean add(String newSinger);

   void update(int id, String name);

   boolean delete(int id);

   boolean exist(int id);
}
