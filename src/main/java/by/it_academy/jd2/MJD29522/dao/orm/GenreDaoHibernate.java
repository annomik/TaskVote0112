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

    public GenreDaoHibernate(IManager managerFactory) {
        this.manager = managerFactory;
    }

    @Override
    public synchronized List<GenreID> get() {
        List<GenreID> genres = new ArrayList<>();
        EntityManager entityManager = manager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            List<GenreEntity> GenreEntityList = entityManager.createQuery(
                    "from GenreEntity ORDER BY id", GenreEntity.class).getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();
            for (GenreEntity genreEntity : GenreEntityList) {
                genres.add(new GenreID(new GenreDTO(genreEntity.getName()), genreEntity.getId()));
            }
            }catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Ошибка в запросе sql");
            }finally {
                entityManager.close();
        }
        return genres;
    }

    @Override
    public synchronized boolean add(String newGenre) {
        EntityManager entityManager = manager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new GenreEntity(newGenre));
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public synchronized void update(long id, String name) {
        EntityManager entityManager = manager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        GenreEntity genreToUpdate = entityManager.find(GenreEntity.class, id);
        genreToUpdate.setName(name);
        entityManager.merge(genreToUpdate);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public synchronized boolean delete(long id) {
        EntityManager entityManager = manager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        GenreEntity genreToRemoved = entityManager.find(GenreEntity.class, id);
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
        EntityManager entityManager = manager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        GenreEntity genre = entityManager.find(GenreEntity.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        if(genre !=null){
            return true;
        }
        return false;
    }
}
