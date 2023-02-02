package by.it_academy.jd2.MJD29522.dao.orm;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.orm.api.IManager;
import by.it_academy.jd2.MJD29522.dao.orm.entity.GenreEntity;
import by.it_academy.jd2.MJD29522.dao.orm.entity.SingerEntity;
import by.it_academy.jd2.MJD29522.dao.orm.entity.VoteEntity;
import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;

import javax.persistence.EntityManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VoteDaoHibernate implements IVoteDao {

    private final IManager manager;

    public VoteDaoHibernate(IManager managerFactory) {
        this.manager = managerFactory;
    }

    @Override
    public synchronized List<Vote> getVoteList() {
        List<Vote> votes = new LinkedList<>();
        EntityManager entityManager = null;

        try {
            entityManager = manager.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
            List<VoteEntity> VoteEntityList = entityManager.createQuery(
                    "from VoteEntity ORDER BY id", VoteEntity.class).getResultList();

            for (VoteEntity voteEntity : VoteEntityList) {
                votes.add(new Vote(voteEntity.getId(),
                        new VoteDTO(voteEntity.getSinger().getId(),
                                arrayLongs(voteEntity.getGenres()),
                                voteEntity.getMessage(),
                                voteEntity.getEmail(),
                                voteEntity.getLocalDate())));
            }
            entityManager.getTransaction().commit();
            return votes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean save(VoteDTO vote) {
        boolean result = false;
        List<GenreEntity> genres = voteToGenreEntityList(vote);
        SingerEntity singer = voteToSingerEntity(vote);
        EntityManager entityManager = null;

        VoteEntity voteEntity= new VoteEntity(singer, genres,
                vote.getMessage(),
                vote.getEmail(),
                vote.getLocalDate()
                );
        try {
            entityManager = manager.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(voteEntity);
            entityManager.getTransaction().commit();
            result = true;
        } catch (Exception e) {
                throw new RuntimeException(e);
        }finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
        return result;
    }

    private long[] arrayLongs(List<GenreEntity> genreEntityList){
        long[] genres = new long[genreEntityList.size()];
        int i = 0;
        for (GenreEntity genreEntity : genreEntityList) {
            genres[i] = genreEntity.getId();
            i++;
        }
        return genres;
    }

    private List <GenreEntity> voteToGenreEntityList (VoteDTO voteDTO){
        List <GenreEntity> genresEntity = new ArrayList<>();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
            for (long genre : voteDTO.getGenresID()) {
                genresEntity.add(entityManager.find(GenreEntity.class,genre));
            }
            return genresEntity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

    private SingerEntity voteToSingerEntity (VoteDTO voteDTO){
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.find(SingerEntity.class,voteDTO.getSingerID());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

}
