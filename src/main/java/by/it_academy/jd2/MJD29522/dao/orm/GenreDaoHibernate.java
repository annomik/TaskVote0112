package by.it_academy.jd2.MJD29522.dao.orm;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.orm.api.IManager;
import by.it_academy.jd2.MJD29522.dao.orm.entity.GenreEntity;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.dto.GenreID;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoHibernate implements IGenreDao {

    private final IManager manager;

    public GenreDaoHibernate(IManager entityManager) {
        this.manager = entityManager;
    }

    @Override
    public List<GenreEntity> get() {
        List<GenreID> genres = new ArrayList<>();

        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<GenreEntity> genreEntityList = entityManager.createQuery
                    ("from GenreEntity ORDER BY id", GenreEntity.class).getResultList();

            entityManager.getTransaction().commit();

            return genreEntityList;
        } catch (Exception e) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Ошибка в базе данных", e);
        } finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public boolean add(String newGenre) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(new GenreEntity(newGenre));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Ошибка в базе данных", e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public void update(GenreEntity genreEntity) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            GenreEntity genreToUpdate = entityManager.find(GenreEntity.class, genreEntity.getId());
            if(genreToUpdate != null){
                entityManager.merge(genreEntity);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Ошибка в базе данных", e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public boolean delete(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            GenreEntity genreToRemoved = entityManager.find(GenreEntity.class, id);
            if(genreToRemoved != null){
                entityManager.remove(genreToRemoved);
            }
            entityManager.getTransaction().commit();
            return genreToRemoved != null;
        } catch (Exception e) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Ошибка в базе данных", e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public boolean exist(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            GenreEntity genre = entityManager.find(GenreEntity.class, id);
            entityManager.getTransaction().commit();
            return genre != null;

        } catch (Exception e) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Ошибка в базе данных", e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }
}
