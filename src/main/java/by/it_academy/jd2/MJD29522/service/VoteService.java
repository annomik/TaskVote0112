package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;

import java.util.*;

public class VoteService implements IVoteService {

    private final IVoteDao dao;
    private final ISingerService singerService;
    private final IGenreService genreService;

    public VoteService(IVoteDao dao, ISingerService singerService, IGenreService genreService) {
        this.dao = dao;
        this.singerService = singerService;
        this.genreService = genreService;
    }

    //убрал тип возвращаемого значения метода save и поменял интерфейс *Дима
    @Override
    public void save(VoteDTO voteDTO) {
        validation(voteDTO);
        this.dao.save(new Vote(voteDTO));
    }

//т.к. жанры могут повторяться, и повторы надо исключить, то придумал вот так: в начале перевожу в сет,
// тем самым убирая повторы, затем обратно в инт. Т.к. инт это примитивный тип данных

// убрал тип возвращаемого значения метода валидации и всю валидацию закинул в лдин метод *Дима
    private void validation(VoteDTO voteDTO){
        int singerID = voteDTO.getExecutorID();
        int[] genresID = voteDTO.getGenresID();

        if(!singerService.exist(singerID)){
            throw new IllegalArgumentException("Исполнителя с id " + singerID + " не существует");
        }

        Set<Integer> set = new HashSet<>();
        for (int genreID : genresID) {
            set.add(genreID);
        }
        if(set.size() != genresID.length){
            throw new IllegalArgumentException("В Вашем голосе жанры дублируются");
        }

        if(genresID.length > 5 || genresID.length < 3){
            throw new IllegalArgumentException("Колличество жанров должно быть от 3 до 5, и жанры не должны повторяться");
        }

        for (int genreID : genresID) {
            if(!genreService.exist(genreID)){
                throw new IllegalArgumentException("Жанра с id " + singerID + " не существует");
            }
        }
        if(voteDTO.getMessage().isBlank()||voteDTO.getMessage().length()==0){
            throw new IllegalArgumentException("Сообщение о себе не может быть пустым");
        }
    }

    @Override
    public List<Vote> getVote() {
        List<Vote> voteDTOs = dao.getVoteList();
        return voteDTOs;
    }
}
