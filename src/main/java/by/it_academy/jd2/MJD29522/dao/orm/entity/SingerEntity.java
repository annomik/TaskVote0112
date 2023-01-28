package by.it_academy.jd2.MJD29522.dao.orm.entity;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table( name = "app.artists" )
//@NamedQuery(name = "SingerEntity.getAll", query = "SELECT c from app.artists c")
public class SingerEntity {
  //      implements ISingerDao {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    private String name;

    public SingerEntity() {
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
