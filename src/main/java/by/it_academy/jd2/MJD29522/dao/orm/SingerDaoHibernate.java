package by.it_academy.jd2.MJD29522.dao.orm;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.orm.api.IManager;
import by.it_academy.jd2.MJD29522.dao.orm.entity.SingerEntity;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingerDaoHibernate implements ISingerDao {

    private final IManager manager;

    public SingerDaoHibernate(IManager entityManager) {
        this.manager = entityManager;
    }

    @Override
    public synchronized List<SingerID> get() {
        List<SingerID> singers = new ArrayList<>();

        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<SingerEntity> genreEntityList = entityManager.createQuery
                    ("from SingerEntity ORDER BY id", SingerEntity.class).getResultList();

            entityManager.getTransaction().commit();
            for (SingerEntity genreEntity : genreEntityList) {
                singers.add(new SingerID(new SingerDTO(genreEntity.getName()), genreEntity.getId()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return singers;
    }

    @Override
    public synchronized boolean add(String newSinger) {
        boolean result = false;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(new SingerEntity(newSinger));
            entityManager.getTransaction().commit();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return result;
    }

    @Override
    public synchronized void update(long id, String name) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerToUpdate = entityManager.find(SingerEntity.class, id);
            singerToUpdate.setName(name);
            entityManager.merge(singerToUpdate);
            entityManager.getTransaction().commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public synchronized boolean delete(long id) {
        boolean result = false;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerToRemoved = entityManager.find(SingerEntity.class, id);
            entityManager.remove(singerToRemoved);
            entityManager.getTransaction().commit();
            if(singerToRemoved !=null){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return false;
    }

    @Override
    public synchronized boolean exist(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singer = entityManager.find(SingerEntity.class, id);
            entityManager.getTransaction().commit();
            if(singer !=null){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return false;
    }
}
