package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;

import java.util.ArrayList;
import java.util.List;

public class SingerDao implements ISingerDao {

  List<String> singers = new ArrayList<>();

   public SingerDao() {

   }

    @Override
    public void save(SingerDTO singerDTO) {

    }

    @Override
   public List<String> getSingerList() {
      return singers;
   };
}
