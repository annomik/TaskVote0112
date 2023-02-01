package by.it_academy.jd2.MJD29522.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app.vote")
public class Vote {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    long id;
    String about;
    String email;

    public Vote(long id, String about, String email) {
        this.id = id;
        this.about = about;
        this.email = email;
    }

    public Vote() {
    }

    public long getId() {
        return id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
