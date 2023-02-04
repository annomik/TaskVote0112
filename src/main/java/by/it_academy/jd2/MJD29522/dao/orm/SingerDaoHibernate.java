package by.it_academy.jd2.MJD29522.dao.orm;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.orm.api.IManager;
import by.it_academy.jd2.MJD29522.dao.orm.entity.SingerEntity;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class SingerDaoHibernate implements ISingerDao {

    private final IManager manager;

    public SingerDaoHibernate(IManager entityManager) {
        this.manager = entityManager;
    }

    @Override
    public List<SingerID> get() {
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
            return singers;
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
    public boolean add(String newSinger) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(new SingerEntity(newSinger));
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
    public void update(long id, String name) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerToUpdate = entityManager.find(SingerEntity.class, id);
            if(entityManager != null){
                singerToUpdate.setName(name);
                entityManager.merge(singerToUpdate);
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
            SingerEntity singerToRemoved = entityManager.find(SingerEntity.class, id);
            if(singerToRemoved != null){
                entityManager.remove(singerToRemoved);
            }
            entityManager.getTransaction().commit();
            return singerToRemoved != null;
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
            SingerEntity singer = entityManager.find(SingerEntity.class, id);
            entityManager.getTransaction().commit();
           return singer != null;
        } catch (Exception e) {
            if(entityManager != null){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Ошибка в базе данных", e);
        }
        finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public String getName(long id) {
        return null;
    }
}
