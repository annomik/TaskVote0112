package by.it_academy.jd2.MJD29522.dto;

import java.time.LocalDate;

public class Vote {

    private final long id;
    private VoteDTO voteDTO;
    public Vote(long id, VoteDTO voteDTO) {
        this.id = id;
        this.voteDTO = voteDTO;
    }

    public long getId() {
        return id;
    }


}
