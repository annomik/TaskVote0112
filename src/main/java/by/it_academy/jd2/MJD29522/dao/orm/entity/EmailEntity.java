package by.it_academy.jd2.MJD29522.dao.orm.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "app.email")
public class EmailEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(name = "massage")
    private String message;
    @Column(name = "validateEmail")
    private boolean validateEmail;
    @Column(name = "sendMassage")
    private boolean sendMassage;
    @Column(name = "lastSendTime")
    private long lastSendTime;

    public EmailEntity() {
    }

    public EmailEntity(long id, String message, boolean validateEmail, boolean sendMassage, long lastSendTime) {
        this.id = id;
        this.message = message;
        this.validateEmail = validateEmail;
        this.sendMassage = sendMassage;
        this.lastSendTime = lastSendTime;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValidateEmail() {
        return validateEmail;
    }

    public void setValidateEmail(boolean validateEmail) {
        this.validateEmail = validateEmail;
    }

    public boolean isSendMassage() {
        return sendMassage;
    }

    public void setSendMassage(boolean sendMassage) {
        this.sendMassage = sendMassage;
    }

    public long getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(long lastSendTime) {
        this.lastSendTime = lastSendTime;
    }
}
