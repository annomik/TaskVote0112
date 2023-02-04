package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.ISendingEmailService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VoteService implements IVoteService {

    private final IVoteDao dao;
    private final ISingerService singerService;
    private final IGenreService genreService;
    private final ISendingEmailService sendingEmailService;
    ExecutorService executorService = Executors.newFixedThreadPool(10);


    public VoteService(IVoteDao dao, ISingerService singerService, IGenreService genreService, ISendingEmailService sendingEmailService) {
        this.dao = dao;
        this.singerService = singerService;
        this.genreService = genreService;
        this.sendingEmailService = sendingEmailService;
    }

    @Override
    public void save(VoteDTO voteDTO) {
        validation(voteDTO);
        this.dao.save(voteDTO);
        Runnable runnable = () -> sendingEmailService.sendEmail(voteDTO);
        executorService.execute(runnable);
    }

   private void validation(VoteDTO voteDTO){
        long singerID = voteDTO.getSingerID();
        long[] genresID = voteDTO.getGenresID();

        if(!singerService.exist(singerID)){
            throw new IllegalArgumentException("Такого исполнителя не существует");
        }

        Set<Long> set = new HashSet<>();
        for (long genreID : genresID){
            set.add(genreID);
        }

        if (set.size() != genresID.length){
            throw new IllegalArgumentException("В Вашем голосе жанры дублируются");
        }

        if (genresID.length > 5 || genresID.length < 3){
            throw new IllegalArgumentException("Количество жанров должно быть от 3 до 5, и жанры не должны повторяться");
        }

        for (long genreID : genresID){
            if(!genreService.exist(genreID)){
                throw new IllegalArgumentException("Жанра с id " + genreID + " не существует.");
            }
        }
        if(voteDTO.getMessage().isBlank()||voteDTO.getMessage().length()==0){
            throw new IllegalArgumentException("Сообщение о себе не может быть пустым");
        }
       if(voteDTO.getMessage().length() > 255){
           throw new IllegalArgumentException("Длина сообщения о себе не должна превышать 255 символов");
       }
       if(voteDTO.getEmail().length() > 255){
           throw new IllegalArgumentException("Длина почты email не должна превышать 255 символов");
       }

   }

    @Override
    public List<Vote> getVote() {
        return dao.getVoteList();
    }
}
