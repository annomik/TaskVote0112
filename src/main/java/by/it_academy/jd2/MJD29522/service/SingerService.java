package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.entity.SingerEntity;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;

import java.util.List;
import java.util.Map;

public class SingerService implements ISingerService {

    private final ISingerDao singerDao;

    public SingerService(ISingerDao singerDao) {
        this.singerDao = singerDao;
    }

    @Override
    public List<SingerEntity> get() {
        return singerDao.get();
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

        String[] parameters = mapParameters.get(parameter);

        if (parameters[0] == null || parameters[0].isBlank()){
            throw new IllegalArgumentException("Введите исполнителя!");
        }

        if (parameters.length > 1){
            throw new IllegalArgumentException("Вы можете ввести только одного исполнителя!");
        }

        String nameForAdd = parameters[0];
        if(nameForAdd.length() > 255){
            throw new IllegalArgumentException("Длина названия исполнителя не должна превышать 255 символов");
        }

        List<SingerEntity> singers = get();
        for (SingerEntity singerID : singers) {
            if (nameForAdd.equals(singerID.getName())) {
                throw new IllegalArgumentException("Исполнитель с именем " + nameForAdd + " уже есть в списке");
            }
        }

        return true;
    }

}
