package by.it_academy.jd2.MJD29522.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class VoteDTO {

    private final long singerID;
    private final long[] genresID;
    private final String message;
    private final String email;

    private final LocalDateTime localDate;

    public VoteDTO(long singerID, long[] genresID, String message, String email, LocalDateTime localDate) {
        this.singerID = singerID;
        this.genresID = genresID;
        this.message = message;
        this.email = email;
        this.localDate = localDate;
    }

    public long getSingerID() {
        return singerID;
    }

    public long[] getGenresID() {
        return genresID;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

}
