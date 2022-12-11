package by.it_academy.jd2.MJD29522.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vote extends VoteDTO{

    private final long time;

    public Vote(VoteDTO voteDTO) {
        super(voteDTO.getExecutorID(), voteDTO.getGenresID(), voteDTO.getMessage());
        this.time = setDate();
    }

    public Vote(VoteDTO voteDTO, long time) {
        super(voteDTO.getExecutorID(), voteDTO.getGenresID(), voteDTO.getMessage());
        this.time = time;
    }

    private long setDate(){
        Date date = new Date();
        return date.getTime();
    }

    public String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date(time));
        return date;
    }

    public long getTime() {
        return time;
    }

}
