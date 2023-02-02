package by.it_academy.jd2.MJD29522.dao.orm.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app1.votes")
public class VoteEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private  Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "app1.vote_artists",
            joinColumns = @JoinColumn( name = "vote_id"),
            inverseJoinColumns = @JoinColumn( nullable = false)
           )
    private SingerEntity singer;

    @ManyToMany
    @JoinTable(
            name = "app1.vote_genres",
            joinColumns = @JoinColumn( name = "vote_id"),
            inverseJoinColumns = @JoinColumn( nullable = false)
    )
    @JoinColumn(name = "vote_id")
    private List<GenreEntity> genres = new ArrayList<>();

    private String message;
    private String email;
    private LocalDate localDate;

    public VoteEntity() {
    }

    public VoteEntity(SingerEntity singer, List<GenreEntity> genres, String message, String email, LocalDate localDate) {
        this.singer = singer;
        this.genres = genres;
        this.message = message;
        this.email = email;
        this.localDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {      return message;    }

    public SingerEntity getSinger() {
        return singer;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


}
