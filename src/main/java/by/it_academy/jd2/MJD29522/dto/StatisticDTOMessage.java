package by.it_academy.jd2.MJD29522.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StatisticDTOMessage {
    private final long time;
    private final String message;

    public StatisticDTOMessage(long time, String message) {
        this.time = time;
        this.message = message;
    }

    public String getDateToString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(time));
    }

    public long getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "StatisticDTOMessage{" +
                "time=" + time +
                ", message='" + message + '\'' +
                '}';
    }
}
