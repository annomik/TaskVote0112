package by.it_academy.jd2.MJD29522.dao.orm;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.orm.api.IManager;
import by.it_academy.jd2.MJD29522.dao.orm.entity.SingerEntity;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.dto.SingerID;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class SingerDaoHibernate implements ISingerDao {

    private final IManager manager;

    public SingerDaoHibernate(IManager managerFactory) {
        this.manager = managerFactory;
    }

    @Override
    public List<SingerID> get() {
        List<SingerID> singers = new ArrayList<>();

        EntityManager entityManager = manager.getEntityManager();
               // createEntityManager();
        entityManager.getTransaction().begin();
     //   TypedQuery<SingerEntity> namedQuery = entityManager.createNamedQuery("Singer.getAll", SingerEntity.class);
      //  List<SingerEntity> resultListSingers = namedQuery.getResultList();
        Query query = entityManager.createNativeQuery(
                "SELECT id, name FROM app.artists ORDER BY id;", SingerEntity.class);
        List<SingerEntity> resultListSingers = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        for (SingerEntity singerEntities : resultListSingers) {
            singers.add(new SingerID(new SingerDTO(singerEntities.getName()),singerEntities.getId()));
        }

        return singers;
    }

    @Override
    public boolean add(String newSinger) {
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new SingerEntity(newSinger));
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public void update(long id, String name) {
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        SingerEntity singer = entityManager.find(SingerEntity.class, id);
        singer.setName(name);
        entityManager.merge(new SingerEntity(name));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public synchronized boolean delete(long id) {
        EntityManager entityManager = manager.getEntityManager();
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

//    public SingerEntity get(long id){
//        EntityManager entityManager = factory.createEntityManager();
//        entityManager.getTransaction().begin();
//        SingerEntity singer = EntityManager.find(SingerEntity.class, id);
//        return singer;
//    }

    @Override
    public synchronized boolean exist(long id) {
        EntityManager entityManager = manager.getEntityManager();
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
