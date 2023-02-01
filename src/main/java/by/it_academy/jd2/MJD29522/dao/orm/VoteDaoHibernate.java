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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VoteDaoHibernate implements IVoteDao {

    private final IManager manager;

    public VoteDaoHibernate(IManager entityManager) {
        this.manager = entityManager;
    }

    @Override
    public List<Vote> getVoteList() {
        List<Vote> votes = new LinkedList<>();

        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<VoteEntity> voteEntityList = entityManager.createQuery
                    ("FROM VoteEntity ORDER BY id", VoteEntity.class).getResultList();
            for (VoteEntity voteEntity : voteEntityList) {
                votes.add(new Vote(voteEntity.getId(),
                        new VoteDTO(voteEntity.getSinger().getId(),
                                arrayLongs(voteEntity.getGenre()),
                                voteEntity.getAbout(),
                                voteEntity.getEmail(),
                                voteEntity.getDate())));
            }
            entityManager.getTransaction().commit();
            return votes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public boolean save(VoteDTO vote) {
        boolean result = false;
        List <GenreEntity> genres = voteToGenreEntityList(vote);
        SingerEntity singer = voteToSingerEntity(vote);
        EntityManager entityManager = null;
        VoteEntity voteEntity = new VoteEntity(vote.getMessage(),
                vote.getEmail(),
                vote.getLocalDate(),
                singer,
                genres);
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(voteEntity);
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
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            for (long genre : voteDTO.getGenresID()) {
                genresEntity.add(entityManager.find(GenreEntity.class,genre));
            }
            return genresEntity;
        } catch (SQLException e) {
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
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.find(SingerEntity.class,voteDTO.getSingerID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }
}
