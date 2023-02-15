package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.dto.SingerDTOWithID;
import by.it_academy.jd2.MJD29522.entity.SingerEntity;

import java.util.List;
import java.util.Map;

public interface ISingerService {

   List<SingerDTOWithID> get();
   SingerDTOWithID getCard(long id);

   boolean add(SingerDTO newSinger);

   void update(long id, long version, SingerDTO singerDTO);

   boolean delete(long id, long version);

   boolean exist(long id);

   String getName(long id);

   boolean validate(SingerDTO singer) ;

}
