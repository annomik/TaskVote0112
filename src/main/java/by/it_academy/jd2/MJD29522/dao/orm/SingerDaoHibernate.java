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

    public SingerDaoHibernate(IManager managerFactory) {
        this.manager = managerFactory;
    }

    @Override
    public synchronized List<SingerID> get() {
        List<SingerID> singers = new ArrayList<>();

        EntityManager entityManager = manager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<SingerEntity> resultListSingers = entityManager.createQuery(
               "from SingerEntity", SingerEntity.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        for (SingerEntity singerEntities : resultListSingers) {
            singers.add(new SingerID(new SingerDTO(singerEntities.getName()),singerEntities.getId()));
        }
        return singers;
    }

    @Override
    public synchronized boolean add(String newSinger) {
        EntityManager entityManager = manager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new SingerEntity(newSinger));
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public synchronized void update(long id, String name) {
        EntityManager entityManager = manager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        SingerEntity singer = entityManager.find(SingerEntity.class, id);
        singer.setName(name);
        entityManager.merge(new SingerEntity(name));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public synchronized boolean delete(long id) {
        EntityManager entityManager = manager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        SingerEntity singer = entityManager.find(SingerEntity.class, id);
        entityManager.remove(singer);
        entityManager.getTransaction().commit();
        entityManager.close();
        if (singer != null) {
            return true;
        }
       return false;
    }


    @Override
    public synchronized boolean exist(long id) {
        EntityManager entityManager = manager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        SingerEntity singer = entityManager.find(SingerEntity.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        if(singer != null){
            return true;
        }
        return false;
    }
}
