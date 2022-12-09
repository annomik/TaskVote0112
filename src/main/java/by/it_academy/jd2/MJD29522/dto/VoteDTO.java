package by.it_academy.jd2.MJD29522.dto;
//test commit
public class VoteDTO {

    private String nameSinger;
    private String [] genre;
    private long time;
    private String info;

    public VoteDTO(String nameSinger, String[] genre, long time, String info) {
        this.nameSinger = nameSinger;
        this.genre = genre;
        this.time = time;
        this.info = info;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
