package by.it_academy.jd2.MJD29522.dto;

import java.util.Set;

public class VoteDTO {

    private final int executorID;
    private final int[] genresID;
    private final String message;

    public VoteDTO(int executorID, int[] genresID, String message) {
        this.executorID = executorID;
        this.genresID = genresID;
        this.message = message;
    }

    public int getExecutorID() {
        return executorID;
    }

    public int[] getGenresID() {
        return genresID;
    }

    public String getMessage() {
        return message;
    }
}
