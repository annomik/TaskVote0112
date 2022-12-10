package by.it_academy.jd2.MJD29522.dto;

import by.it_academy.jd2.MJD29522.dto.VoteDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vote {

    private final long time;
    private final VoteDTO voteDTO;

    public Vote(VoteDTO voteDTO) {
        this.time = setDate();
        this.voteDTO = voteDTO;
    }

    public Vote(long time, VoteDTO voteDTO) {
        this.time = time;
        this.voteDTO = voteDTO;
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

    public VoteDTO getVoteDTO() {
        return voteDTO;
    }
}
