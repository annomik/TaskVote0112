package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.Singer;
import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;

import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;


public class SingerService implements ISingerService {

    private final ISingerDao singerDao;

    public SingerService(ISingerDao singerDao) {
        this.singerDao = singerDao;
    }

    @Override
    public void save(SingerDTO singerDTO) {
    }

    @Override
    public boolean validate(SingerDTO singerDTO) {
        boolean result = false;
        for (int i = 0; i < singerDao.getSingerList().size(); i++) {
            if (singerDTO.getNameSinger().equals(singerDao.getSingerList().get(i))) {
                result = true;
            }
        }
        return result;
    }


//    public String getSingers(){
//        String result = "";
//        for (Singer singer : singers) {
//            result = result + "<p>Исполнитель: " + singer.getName() + "</p>";
//        }
//        return result;
//    }


}
