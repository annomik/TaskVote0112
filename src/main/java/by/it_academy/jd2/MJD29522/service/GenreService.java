package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.Singer;
import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;



public class GenreService implements IGenreService {

    private final IGenreDao dao;

    public GenreService(IGenreDao dao) {
        this.dao = dao;
    }

    public GenreService() {

    }


    @Override
    public void save(GenreDTO genreDTO) {

    }

    @Override
    public void validate(GenreDTO genreDTO) {


        if (SingerDTO

        String[] singersParams = paramMap.get("singer");
        String result = "";
        if (singersParams.length == 1) {
            for (int i = 0; i < singers.size(); i++) {
                if (service.singers.get(i).getName().equals(singersParams[0])) {
                    service.singers.get(i).addCountVote();
                    result = result + "<p>Спасибо за Ваш голос в наминации лучшего исполнителя!</p>";
                }
            }
        } else {
            result = "<p>Необходимо проголосовать за одного исполнителя! Повторите голосование!</p>";
        }
    }

//    public String getSingers(){
//        String result = "";
//        for (Singer singer : singers) {
//            result = result + "<p>Исполнитель: " + singer.getName() + "</p>";
//        }
//        return result;
//    }


}
