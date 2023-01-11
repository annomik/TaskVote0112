package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import java.util.ArrayList;
import java.util.List;

public class SingerDao implements ISingerDao {

  private List<SingerID> singers = new ArrayList<>();

    public SingerDao(List<SingerID> singers) {
        this.singers = singers;
    }

    public SingerDao(){
        this.singers.add(new SingerID(new SingerDTO("Shakira"), 1));
        this.singers.add(new SingerID(new SingerDTO("Цой"),2));
        this.singers.add(new SingerID(new SingerDTO("Madonna"),3));
        this.singers.add(new SingerID(new SingerDTO("Linkin Park"),4));
    }

    @Override
    public List<SingerID> get() {
        return  singers;
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
        for (SingerID singerID: singers){
            if(id == singerID.getId()){
                return true;
            }
        }
        return false;
    }
}
