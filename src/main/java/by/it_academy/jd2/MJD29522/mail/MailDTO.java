package by.it_academy.jd2.MJD29522.mail;

import java.time.LocalDate;

public class MailDTO {
    private final String massage;
    private final String email;
    private final LocalDate createVote;
    private boolean statusSend;
    private LocalDate lastSend;

    public MailDTO(String message, String email, LocalDate createVote, boolean statusSend, LocalDate lastSend) {
        this.massage = message;
        this.email = email;
        this.createVote = createVote;
        this.statusSend = statusSend;
        this.lastSend = lastSend;
    }

    public String getMassage() {
        return massage;
    }

    public void setLastSend(LocalDate lastSend) {
        this.lastSend = lastSend;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreateVote() {
        return createVote;
    }

    public boolean isStatusSend() {
        return statusSend;
    }

    public void setStatusSend(boolean statusSend) {
        this.statusSend = statusSend;
    }

    public LocalDate getLastSend() {
        return lastSend;
    }

    public void setLastSend() {
        this.lastSend = LocalDate.now();
    }
}
