package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dto.*;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.api.IStatisticService;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;
import by.it_academy.jd2.MJD29522.service.fabrics.GenreServiceSingleton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

//я в начале хотел сделать как предложил дима, но в статистику мы все равно должно положить всех артистов(жанры), поэтому
// надо у сервисов просить всю ифу по жанрам и автистам, и уже сравнивать здесь. Т.к. все равно по списку бегать.
public class StatisticService implements IStatisticService {

    private final ISingerService singerService;
    private final IGenreService genreService;
    private final IVoteService voteService;

    public StatisticService(ISingerService singerService, IGenreService genreService, IVoteService voteService) {
        this.singerService = singerService;
        this.genreService = genreService;
        this.voteService = voteService;
    }


    //может стоит проверки на ноль добавить, но мне влом этим сейчас заниматься
    @Override
    public List<StatisticDTOArtistOrGenre> getResultGenre() {
        List<GenreID> genres = genreService.get();
        List<Vote> votes = voteService.getVote();
        List<StatisticDTOArtistOrGenre> statisticGenre = new ArrayList<>();
        // в этом цикле происходит заполнение именами. т.к. я заполняю ид и имена, то сравнивать проще по ид, в след методах все однотипно
        for(GenreID  genreID : genres){
            statisticGenre.add(new StatisticDTOArtistOrGenre(genreID.getId(),genreID.getGenreDTO().getName()));
        }
        for(Vote vote : votes){
            for(int i = 0;i<vote.getGenresID().length;i++){                         //берем каждый существующий жанр в голосе
                boolean notAddingCount = true;
                for(int j = 0;j<statisticGenre.size();j++){                         //ничего лучшего не придумал, как обратиться к элементу на прямую без цикла???
                        if(statisticGenre.get(j).getId()==vote.getGenresID()[i]){
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
        List<SingerID> singers = singerService.get();
        List<Vote> votes = voteService.getVote();
        List<StatisticDTOArtistOrGenre> statisticSinger = new ArrayList<>();
        for(SingerID singerID: singers){                                    // в этом цикле происходит заполнение именами
            statisticSinger.add(new StatisticDTOArtistOrGenre(singerID.getId(),singerID.getSingerDTO().getName()));
        }
        for(Vote vote : votes){
            for(int i = 0;i<statisticSinger.size();i++){
                boolean notAddingCount = true;
                if(vote.getExecutorID()==statisticSinger.get(i).getId()){
                    statisticSinger.get(i).addCount();
                    notAddingCount = false;
                    break;
                }
                if(notAddingCount){
                    throw new ArrayStoreException("Такого исполнителя не существует в жанрах");
                }
            }
        }
        List<StatisticDTOArtistOrGenre> sortList = statisticSinger.stream().sorted(Comparator.comparing(StatisticDTOArtistOrGenre::getCount).reversed()).collect(Collectors.toList());
        return sortList;
    }

    @Override
    public List<StatisticDTOMessage> getResultMessage() {
        List<Vote> votes = voteService.getVote();
        List<StatisticDTOMessage> messages = new ArrayList<>();
        for(Vote vote : votes){
            messages.add(new StatisticDTOMessage(vote.getTime(),vote.getMessage()));
        }
        List<StatisticDTOMessage> sortList = messages.stream().sorted(Comparator.comparing(StatisticDTOMessage::getTime)).collect(Collectors.toList());
        return sortList;
    }

}
