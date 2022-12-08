package by.it_academy.jd2.MJD29522.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vote {

    private String singer;
    private String [] genre;
    private long time;
    private String info;

    public Vote(String singer, String[] genre, long time, String info) {
        this.singer = singer;
        this.genre = genre;
        this.info = info;
        setDate();
    }

    public Vote() {

    }

    private void setDate(){
        Date date = new Date();
        time = date.getTime();
    }

    public String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date(time));
        return date;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
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
