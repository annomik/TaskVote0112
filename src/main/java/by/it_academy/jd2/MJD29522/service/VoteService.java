package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.Vote;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;

import java.util.ArrayList;
import java.util.List;

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
        boolean itsOK = true;
        itsOK = (validationExecutors(vote.getExecutorID())&&validationGenres(vote.getGenresID()));
        if(itsOK){
            if(vote.getMessage().isBlank()||vote.getMessage().length()==0){
                itsOK = false;
            }
        } else
            return false;
        if(itsOK){
            itsOK = dao.save(vote);
        }
        return itsOK;
    }

    private boolean validationExecutors(int singerID){
        if(singerService.exist(singerID))
            throw new IllegalArgumentException("Singer with id "+singerID+" don't exist");
        return true;
    }

    private boolean validationGenres(int[] genresID){
        for(int i = 0;i<genresID.length;i++){
            if(!genreService.exist(genresID[i])){
                throw new IllegalArgumentException("Genre with id "+genresID[i]+" don't exist");
            }
        }
        return true;
    }

    @Override
    public List<Vote> getVoteDTO() {
        List<Vote> voteDTOs = dao.getVoteList();
        return voteDTOs;
    }
}
