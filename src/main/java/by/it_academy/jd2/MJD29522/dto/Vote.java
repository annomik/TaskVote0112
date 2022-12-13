package by.it_academy.jd2.MJD29522.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vote extends VoteDTO{

    private final long time;

    public Vote(VoteDTO voteDTO) {
        super(voteDTO.getSingerID(), voteDTO.getGenresID(), voteDTO.getMessage());
        this.time = setDate();
    }

    public Vote(VoteDTO voteDTO, long time) {
        super(voteDTO.getSingerID(), voteDTO.getGenresID(), voteDTO.getMessage());
        this.time = time;
    }

    private long setDate(){
        Date date = new Date();
        return date.getTime();
    }

    public String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(time));
    }

    public long getTime() {
        return time;
    }

}
