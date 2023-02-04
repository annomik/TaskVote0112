package by.it_academy.jd2.MJD29522.dto;

import java.util.Date;

public class StatisticDTOMessage {
    private final Date time;
    private final String message;

    public StatisticDTOMessage(Date time, String message) {
        this.time = time;
        this.message = message;
    }

    public Date getTime() {
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
