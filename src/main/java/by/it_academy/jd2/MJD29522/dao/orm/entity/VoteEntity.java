package by.it_academy.jd2.MJD29522.dao.orm.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app.votes")
public class VoteEntity {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "about")
    private String about;

    @Column(name = "email")
    private String email;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinTable(
            name="app.vote_singer",
            joinColumns=
            @JoinColumn(name="vote_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="singer_ID", referencedColumnName="ID")
    )
    private SingerEntity singer;

    @ManyToMany
    @JoinTable(
            name="app.vote_genre",
            joinColumns=
            @JoinColumn(name="vote_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="genre_ID", referencedColumnName="ID")
    )
    private List<GenreEntity> genres;

    public VoteEntity() {
    }

    public VoteEntity(String about, String email, Date date, SingerEntity singer, List<GenreEntity> genres) {
        this.about = about;
        this.email = email;
        this.date = date;
        this.singer = singer;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public String getAbout() {
        return about;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate() {
        return date;
    }

    public SingerEntity getSinger() {
        return singer;
    }

    public List<GenreEntity> getGenre() {
        return genres;
    }
}
