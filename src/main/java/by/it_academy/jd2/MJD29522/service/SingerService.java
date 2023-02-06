package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.orm.entity.SingerEntity;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SingerService implements ISingerService {

    private final ISingerDao singerDao;

    public SingerService(ISingerDao singerDao) {
        this.singerDao = singerDao;
    }

    @Override
    public List<SingerID> get() {
        List<SingerEntity> singerEntityList;
        List<SingerID> singerIDList = new ArrayList<>();
        try {
            singerEntityList = singerDao.get();
            for (SingerEntity singerEntity : singerEntityList) {
                singerIDList.add(new SingerID(new SingerDTO(singerEntity.getName()), singerEntity.getId()));
            }
        return singerIDList;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(String newSinger) {
        return singerDao.add(newSinger);
    }

    @Override
    public void update(long id, String name) {
        if (singerDao.exist(id)) {
            SingerEntity singerEntity = new SingerEntity(id, name);
            singerDao.update(singerEntity);
        } else throw new IllegalArgumentException("Исполнителя с id " + id + " для обновления не нейдено!");
    }

    @Override
    public boolean delete(long id) {
        return singerDao.delete(id);
    }

    @Override
    public boolean exist(long id) {
        return this.singerDao.exist(id);
    }

    @Override
    public String getName(long id) {
        return this.singerDao.getName(id);
    }

    @Override
    public boolean validate(Map<String, String[]> mapParameters, String parameter) {

        List<SingerID> singers = get();
        String[] parameters = mapParameters.get(parameter);

        if (parameters[0] == null || parameters[0].isBlank()){
            throw new IllegalArgumentException("Введите исполнителя!");
        }

        if (parameters.length > 1){
            throw new IllegalArgumentException("Вы можете ввести только одного исполнителя!");
        }

        String nameForAdd = parameters[0];
        for (SingerID singerID : singers) {
            if (nameForAdd.equals(singerID.getSingerDTO().getName())) {
                throw new IllegalArgumentException("Исполнитель с именем " + nameForAdd + " уже есть в списке");
            }
        }
        if(nameForAdd.length() > 255){
            throw new IllegalArgumentException("Длина названия исполнителя не должна превышать 255 символов");
        }
        return true;
    }

}
