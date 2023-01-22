package by.it_academy.jd2.MJD29522.dao.memory;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import java.util.ArrayList;
import java.util.List;

public class SingerDaoMemory implements ISingerDao {

    private List<SingerID> singers = new ArrayList<>();

    private volatile long countID = 0;

    public SingerDaoMemory(List<SingerID> singers) {
        this.singers = singers;
    }

    public SingerDaoMemory(){
        this.singers.add(new SingerID(new SingerDTO("Shakira"), generateId()));
        this.singers.add(new SingerID(new SingerDTO("Цой"),generateId()));
        this.singers.add(new SingerID(new SingerDTO("Madonna"),generateId()));
        this.singers.add(new SingerID(new SingerDTO("Linkin Park"),generateId()));
    }

    @Override
    public List<SingerID> get() {
        return  singers;
    }

    public long generateId(){
        synchronized (SingerDaoMemory.class){
            countID++;
        }
        return  countID;
    }

    @Override
    public boolean add(String newSinger) {
        if(!exist(newSinger)){
            singers.add(new SingerID(new SingerDTO(newSinger), generateId()));
            return  true;
        } else {
            return false;
        }
    }

    @Override
    public void update(long id, String name) {
        for (SingerID singer: singers){
            if(singer.getId() == id){
                singers.set(singers.indexOf(singer), new SingerID(new SingerDTO(name),id));
            }
        }
    }

    @Override
    public boolean delete(long id) {
        for (SingerID singerID: singers){
                if (singerID.getId() == id){
                    singers.remove(singerID);
                    return true;
                }
            }
        return false;
    }

    @Override
    public boolean exist(long id) {
        for (SingerID singerID: singers){
            if(id == singerID.getId()){
                return true;
            }
        }
        return false;
    }

    public boolean exist(String singer) {
        for (SingerID singerID: singers){
            if(singer.equals(singerID.getSingerDTO().getName())){
                return true;
            }
        }
        return false;
    }
}
