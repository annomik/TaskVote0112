package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.entity.Singer;
import by.it_academy.jd2.MJD29522.dao.manager.api.IManager;

import javax.persistence.EntityManager;
import java.util.List;

public class SingerDaoHibernate implements ISingerDao {
    private final IManager manager;

    public SingerDaoHibernate(IManager manager) {
        this.manager = manager;
    }


    @Override
    public List<Singer> get() {
        List<Singer> singers;
        EntityManager entityManager = null;
        try{
            entityManager.getTransaction().begin();
            singers = entityManager.createQuery("from Singer",Singer.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (RuntimeException e){
            throw new RuntimeException("Error for DataBase singers");
        } finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return singers;
     }

    @Override
    public boolean add(String newSinger) {
        EntityManager entityManager;,
        return true;
    }

    @Override
    public void update(long id, String name) {

    }

    @Override
    public boolean delete(long id) {

    }

    @Override
    public boolean exist(long id) {

    }

    @Override
    public String getName(long id) {

    }
}
