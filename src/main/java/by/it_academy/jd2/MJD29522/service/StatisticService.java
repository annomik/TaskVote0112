package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dto.*;
import by.it_academy.jd2.MJD29522.entity.GenreEntity;
import by.it_academy.jd2.MJD29522.entity.SingerEntity;
import by.it_academy.jd2.MJD29522.entity.VoteEntity;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.api.IStatisticService;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class  StatisticService implements IStatisticService {

    private final ISingerService singerService;
    private final IGenreService genreService;
    private final IVoteService voteService;

    public StatisticService(ISingerService singerService, IGenreService genreService, IVoteService voteService) {
        this.singerService = singerService;
        this.genreService = genreService;
        this.voteService = voteService;
    }


    @Override
    public List<StatisticDTOArtistOrGenre> getResultGenre() {
        List<GenreEntity> genres = genreService.get();
        List<VoteEntity> votes = voteService.getVote();
        List<StatisticDTOArtistOrGenre> statisticGenre = new ArrayList<>();
        // в этом цикле происходит заполнение именами. т.к. я заполняю ид и имена, то сравнивать проще по ид, в след методах все однотипно
        for(GenreEntity  genreID : genres){
            statisticGenre.add(new StatisticDTOArtistOrGenre(genreID.getId(),genreID.getName()));
        }

        for(VoteEntity vote : votes){
            for(int i = 0;i<vote.getGenre().size();i++){                         //берем каждый существующий жанр в голосе
                boolean notAddingCount = true;
                for(int j = 0;j<statisticGenre.size();j++){                         //ничего лучшего не придумал, как обратиться к элементу на прямую без цикла???
                        if(statisticGenre.get(j).getId()==vote.getGenre().get(i).getId()){
                            statisticGenre.get(j).addCount();
                            notAddingCount = false;
                            break;
                        }
                }
                if(notAddingCount){
                    throw new ArrayStoreException("Такого голоса не существует в жанрах");
                }
            }
        }
        List<StatisticDTOArtistOrGenre> sortList = statisticGenre.stream().sorted(Comparator.comparing(StatisticDTOArtistOrGenre::getCount).reversed()).collect(Collectors.toList());
        return sortList;
    }

    @Override
    public List<StatisticDTOArtistOrGenre> getResultSinger() {
        List<SingerEntity> singers = singerService.get();
        List<VoteEntity> votes = voteService.getVote();
        List<StatisticDTOArtistOrGenre> statisticSinger = new ArrayList<>();
        for(SingerEntity singerID: singers){                                    // в этом цикле происходит заполнение именами
            statisticSinger.add(new StatisticDTOArtistOrGenre(singerID.getId(),singerID.getName()));
        }
        for(VoteEntity vote : votes){
            boolean notAddingCount = true;
            for(int i = 0;i<statisticSinger.size();i++){
                if(vote.getSinger().getId()==statisticSinger.get(i).getId()){
                    statisticSinger.get(i).addCount();
                    notAddingCount = false;
                    break;
                }
            }
            if(notAddingCount){
                throw new ArrayStoreException("Такого исполнителя не существует в жанрах");
            }
        }
        List<StatisticDTOArtistOrGenre> sortList = statisticSinger.stream().sorted(Comparator.comparing(StatisticDTOArtistOrGenre::getCount).reversed()).collect(Collectors.toList());
        return sortList;
    }
    @Override
    public List<StatisticDTOMessage> getResultMessage() {
        List<VoteEntity> votes = voteService.getVote();
        List<StatisticDTOMessage> messages = new ArrayList<>();
        for(VoteEntity vote : votes){
            messages.add(new StatisticDTOMessage(vote.getDate(),vote.getAbout()));
        }
        List<StatisticDTOMessage> sortList = messages.stream().sorted(Comparator.comparing(StatisticDTOMessage::getTime)).collect(Collectors.toList());
        return sortList;
    }

}
