package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import java.util.List;
import java.util.Map;

public class SingerService implements ISingerService {

    private final ISingerDao singerDao;

    public SingerService(ISingerDao singerDao) {
        this.singerDao = singerDao;
    }

    @Override
    public List<SingerID> get() {
        return singerDao.get();
    }

    @Override
    public boolean add(String newSinger) {
        return singerDao.add(newSinger);
    }

    @Override
    public void update(long id, String name) { singerDao.update(id, name); }

    @Override
    public boolean delete(long id) {
        return singerDao.delete(id);
    }

    @Override
    public boolean exist(long id) {
        return this.singerDao.exist(id);
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
        return true;
    }
}
