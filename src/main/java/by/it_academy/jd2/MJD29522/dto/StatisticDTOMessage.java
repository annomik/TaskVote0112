package by.it_academy.jd2.MJD29522.dto;

import java.time.LocalDate;

public class StatisticDTOMessage {
    private final LocalDate time;
    private final String message;

    public StatisticDTOMessage(LocalDate time, String message) {
        this.time = time;
        this.message = message;
    }

    public LocalDate getTime() {
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
