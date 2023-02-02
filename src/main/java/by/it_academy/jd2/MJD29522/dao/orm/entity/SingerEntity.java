package by.it_academy.jd2.MJD29522.dao.orm.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table( name = "app1.artists" )
public class SingerEntity {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    private String name;

    public SingerEntity(String name, VoteEntity voteEntity) {
        this.name = name;
    }

    public SingerEntity() {
    }

    public SingerEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SingerEntity(Long id) {
        this.id = id;
    }

    public SingerEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
