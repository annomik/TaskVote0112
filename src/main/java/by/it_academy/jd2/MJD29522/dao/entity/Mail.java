package by.it_academy.jd2.MJD29522.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "app.mail")
public class Mail {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    private String massage;
    private String email;
    private SimpleDateFormat createVote;
    private boolean statusSend;
    private boolean statusEmail;
    private SimpleDateFormat lastSend;

    public Mail(long id, String massage, String email, SimpleDateFormat createVote, boolean statusSend, boolean statusEmail, SimpleDateFormat lastSend) {
        this.id = id;
        this.massage = massage;
        this.email = email;
        this.createVote = createVote;
        this.statusSend = statusSend;
        this.statusEmail = statusEmail;
        this.lastSend = lastSend;
    }

    public Mail() {
    }

    public long getId() {
        return id;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SimpleDateFormat getCreateVote() {
        return createVote;
    }

    public void setCreateVote(SimpleDateFormat createVote) {
        this.createVote = createVote;
    }

    public boolean isStatusSend() {
        return statusSend;
    }

    public void setStatusSend(boolean statusSend) {
        this.statusSend = statusSend;
    }

    public boolean isStatusEmail() {
        return statusEmail;
    }

    public void setStatusEmail(boolean statusEmail) {
        this.statusEmail = statusEmail;
    }

    public SimpleDateFormat getLastSend() {
        return lastSend;
    }

    public void setLastSend(SimpleDateFormat lastSend) {
        this.lastSend = lastSend;
    }
}
