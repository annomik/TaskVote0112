package by.it_academy.jd2.MJD29522.dto;

import java.time.LocalDate;

public class Vote extends VoteDTO{

    private final long id;
    private final LocalDate time;

    public Vote(long id, VoteDTO voteDTO) {
        super(voteDTO.getSingerID(), voteDTO.getGenresID(), voteDTO.getMessage(), voteDTO.getEmail());
        this.time = LocalDate.now();
        this.id = id;
    }

    public Vote(long id, VoteDTO voteDTO, LocalDate localDate){
        super(voteDTO.getSingerID(),voteDTO.getGenresID(),voteDTO.getMessage(),voteDTO.getEmail());
        this.time = localDate;
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public long getId() {
        return id;
    }


}
