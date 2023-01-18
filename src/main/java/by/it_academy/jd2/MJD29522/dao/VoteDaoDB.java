package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.util.StartingDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class VoteDaoDB implements IVoteDao {
    private final String saveVoteSQL = "INSERT INTO app.votes (about, date) VALUES (?,now());";
    private final String saveVoteArtistSQL = "INSERT INTO app.vote_artist(artist_id,vote_id) VALUES (?,?)";
    private final String saveVoteGenresSQL = "INSERT INTO app.vote_genre(genre_id,vote_id) VALUES (?,?)";
    private final String getVoteSQL =
            "SELECT app.votes.id, about, date, genres_arr.genres, artist_id AS artist " +
            "FROM app.votes " +
                "FULL OUTER JOIN (" +
                    "SELECT vote_id, array_agg(genre_id) AS genres " +
                    "FROM app.vote_genres " +
                    "GROUP BY vote_id) genres_arr " +
                    "ON id=vote_id " +
                "FULL OUTER JOIN app.vote_artist ON id = app.vote_artist.vote_id;";
    @Override
    public List<Vote> getVoteList() {
        List<Vote> votes = new LinkedList<>();
        StartingDB startingDB = new StartingDB();
        try {
            Connection connection = startingDB.load();
            PreparedStatement preparedStatement = connection.prepareStatement(getVoteSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getLong("app.votes.id");
                String about = resultSet.getString("about");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                Array array = resultSet.getArray("genres_arr.genres");
                Long[] genresLong = (Long[]) array.getArray();
                long artist = resultSet.getLong("artist");
                //------------------------------------------------------------------------------
                votes.add(new Vote(id,new VoteDTO(artist,arrayLongs(genresLong),about,""),date));
                //------------------------------------------------------------------------------
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean save(VoteDTO vote) {
        try {
           StartingDB startingDB = new StartingDB();
           Connection connection = startingDB.load();
           PreparedStatement preparedStatement = connection.prepareStatement(saveVoteSQL,
                   Statement.RETURN_GENERATED_KEYS
           );
           preparedStatement.setString(1,vote.getMessage());
           preparedStatement.executeUpdate();
           ResultSet resultSetForID = preparedStatement.getGeneratedKeys();
           resultSetForID.next();
           long newID = resultSetForID.getLong(1);
           saveVoteGenres(newID,vote);
           saveVoteArtist(newID,vote);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private boolean saveVoteArtist(long id, VoteDTO vote){
        StartingDB startingDB = new StartingDB();
        try {
            Connection connection = startingDB.load();
            PreparedStatement preparedStatement = connection.prepareStatement(saveVoteArtistSQL);
            preparedStatement.setLong(1,vote.getSingerID());
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private boolean saveVoteGenres(long id, VoteDTO voteDTO){
        StartingDB startingDB = new StartingDB();
        try {
            Connection connection = startingDB.load();
            PreparedStatement preparedStatement = connection.prepareStatement(saveVoteGenresSQL);
            long[] genres = voteDTO.getGenresID();
            for(long genre : genres){
                preparedStatement.setLong(1,genre);
                preparedStatement.setLong(2,id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private long[] arrayLongs(Long[] longsOld){
        long[] genres = new long[longsOld.length];
        int i = 0;
        for(long a : longsOld){
            genres[i] = a;
            i++;
        }
        return genres;
    }
}
