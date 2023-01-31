package by.it_academy.jd2.MJD29522.mail;

import java.time.LocalDate;

public class MailID extends MailDTO{
    private final long id;

    public MailID(long id, String message, String email, LocalDate createVote, boolean statusSend, boolean statusEmail, LocalDate lastSend) {
        super(message, email, createVote, statusSend, statusEmail, lastSend);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
