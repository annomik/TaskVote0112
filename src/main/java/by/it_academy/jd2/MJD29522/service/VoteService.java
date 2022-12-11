package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class VoteService implements IVoteService {

    private final IVoteDao dao;
    private final ISingerService singerService;
    private final IGenreService genreService;

    public VoteService(IVoteDao dao, ISingerService singerService, IGenreService genreService) {
        this.dao = dao;
        this.singerService = singerService;
        this.genreService = genreService;
    }

    @Override
    public boolean save(VoteDTO vote) {
        boolean itsOK;
        if(vote.getMessage().isBlank()||vote.getMessage().length()==0){
            throw new IllegalArgumentException("Сообщение не может быть пустым");
        }
        validationSinger(vote.getExecutorID());
        int[] newGenres = validationGenres(vote.getGenresID());
        itsOK = dao.save(new VoteDTO(vote.getExecutorID(),newGenres,vote.getMessage()));
        return itsOK;
    }

    private boolean validationSinger(int singerID){
        if(!singerService.exist(singerID))
            throw new IllegalArgumentException("Singer with id "+singerID+" don't exist");
        return true;
    }

    private int[] validationGenres(int[] genresID){
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<genresID.length;i++){
            set.add(genresID[i]);
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            if(!genreService.exist(iterator.next())){
                iterator.remove();
            }
        }
        if(set.size()>5||set.size()<3){
            throw new IllegalArgumentException("Колличество жанров должно быть от 3 до 5, и жанры не должны повторяться");
        }
        int[] newCountGenres = new int[set.size()];
        int i = 0;
        iterator = set.iterator();
        while (iterator.hasNext()){
            newCountGenres[i] = iterator.next();
            i++;
        }
        return newCountGenres;
    }


    @Override
    public List<Vote> getVote() {
        List<Vote> voteDTOs = dao.getVoteList();
        return voteDTOs;
    }
}
