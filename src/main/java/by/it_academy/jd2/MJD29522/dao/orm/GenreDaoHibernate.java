package by.it_academy.jd2.MJD29522.dao.orm;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.ds.api.IDataSourceWrapper;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.dto.GenreID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoHibernate implements IGenreDao {

    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("tutorial");

    @Override
    public synchronized List<GenreID> get() {
        List<GenreID> genres = new ArrayList<>();
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery(
                "SELECT id, name FROM app.genres ORDER BY id;", GenreHibernate.class);
        List <GenreHibernate> genreHibernateList = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        for (GenreHibernate genreHibernate : genreHibernateList) {
            genres.add(new GenreID(new GenreDTO(genreHibernate.getName()), genreHibernate.getId()));
        }
        return genres;
    }

    @Override
    public synchronized boolean add(String newGenre) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new GenreHibernate(newGenre));
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public synchronized void update(long id, String name) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        GenreHibernate genreToUpdate = entityManager.find(GenreHibernate.class, id);
        genreToUpdate.setName(name);
        entityManager.merge(genreToUpdate);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public synchronized boolean delete(long id) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        GenreHibernate genreToRemoved = entityManager.find(GenreHibernate.class, id);
        entityManager.remove(genreToRemoved);
        entityManager.getTransaction().commit();
        entityManager.close();
        if(genreToRemoved !=null){
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean exist(long id) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        GenreHibernate genre = entityManager.find(GenreHibernate.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        if(genre !=null){
            return true;
        }
        return false;
    }
}
