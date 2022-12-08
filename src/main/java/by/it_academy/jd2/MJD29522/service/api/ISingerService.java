package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dto.SingerDTO;


public interface ISingerService {

    void save(SingerDTO singerDTO);

    boolean validate(SingerDTO singerDTO);


}
