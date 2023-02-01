package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.entity.Genre;
import by.it_academy.jd2.MJD29522.dao.manager.api.IManager;

import javax.persistence.EntityManager;
import java.util.List;

public class GenreDaoHibernate implements IGenreDao {
    private final IManager manager;
    public GenreDaoHibernate(IManager manager){
        this.manager = manager;
    }
    @Override
    public List<Genre> get() {
        List<Genre> genres;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManagerFactory();
            entityManager.getTransaction().begin();
            genres = entityManager.createQuery("from Genre", Genre.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (RuntimeException e){
            throw new RuntimeException("Error for DataBase genres");
        } finally {
            if(entityManager!=null)
                entityManager.close();
        }
        return genres;
    }

    @Override
    public synchronized boolean add(String newGenre) {
        EntityManager entityManager = null;
        Genre genre = new Genre();
        genre.setName(newGenre);
        try {
            entityManager = manager.getEntityManagerFactory();
            entityManager.getTransaction().begin();
            entityManager.persist(genre);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e){
            throw new RuntimeException("Error for DataBase genres");
        } finally {
            if(entityManager!=null)
                entityManager.close();
        }
        return true;
    }

    @Override
    public synchronized void update(long id, String name) {
        EntityManager entityManager = null;
        try{
            entityManager.getTransaction().begin();
            Genre genreFromDB = entityManager.find(Genre.class,id);
            if(genreFromDB!=null){
                entityManager.merge(new Genre(id,name));
            }
            entityManager.getTransaction().commit();
            if(genreFromDB==null){
                throw new IllegalArgumentException("This id is not found");
            }
        } catch (RuntimeException e){
            throw new RuntimeException("Error for DataBase genres");
        } finally {
            if(entityManager!=null)
                entityManager.close();
        }
    }

    @Override
    public synchronized boolean delete(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManagerFactory();
            entityManager.getTransaction().begin();
            Genre genreDB = entityManager.find(Genre.class,id);
            if(genreDB!=null){
                entityManager.remove(genreDB);
            }
            if(genreDB==null){
                throw new IllegalArgumentException("This id id not found");
            }
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error for DataBase genres");
        } finally {
            if(entityManager!=null)
                entityManager.close();
        }
        return true;
    }

    @Override
    public boolean exist(long id) {
        boolean found = false;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManagerFactory();
            entityManager.getTransaction().begin();
            Genre genre = entityManager.find(Genre.class,id);
            if(genre!=null){
                found = true;
            }
            entityManager.getTransaction().commit();
        } catch (RuntimeException e){
            throw new RuntimeException("Error for DataBase genres");
        } finally {
            if(entityManager!=null)
                entityManager.close();
        }
        return found;
    }

    @Override
    public String getName(long id) {
        String name = null;
        EntityManager entityManager = null;
        try {
            entityManager.getTransaction().begin();
            Genre genre = entityManager.find(Genre.class,id);
            if(genre!=null){
                name = genre.getName();
            }
            entityManager.getTransaction().commit();
            if(genre==null){
                throw new IllegalArgumentException("This id is not found");
            }
        } catch (RuntimeException e){
            throw new RuntimeException("Error for DataBase genres");
        } finally {
            if(entityManager!=null)
                entityManager.close();
        }
        return name;
    }
}
