package by.it_academy.jd2.MJD29522.dto;

public class VoteDTO {

    private final int singerID;
    private final int[] genresID;
    private final String message;

    public VoteDTO(int singerID, int[] genresID, String message) {
        this.singerID = singerID;
        this.genresID = genresID;
        this.message = message;
    }

    public int getSingerID() {
        return singerID;
    }

    public int[] getGenresID() {
        return genresID;
    }

    public String getMessage() {
        return message;
    }
}
