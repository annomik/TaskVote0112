package by.it_academy.jd2.MJD29522.dao.dataBase;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dao.SrartingDBSingleton;
import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.api.IDataSourceWrapper;
import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.util.StartingDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class VoteDaoDB implements IVoteDao {
    private final IDataSourceWrapper ds;
    private final String saveVoteSQL = "INSERT INTO app.votes (about, date, email) VALUES (?,now(), ?);";
    private final String saveVoteArtistSQL = "INSERT INTO app.vote_artist(artist_id,vote_id) VALUES (?,?)";
    private final String saveVoteGenresSQL = "INSERT INTO app.vote_genres(genre_id,vote_id) VALUES (?,?)";
    private final String getVoteSQL =
                    "SELECT id, about, date, email, genres, artist_id AS artist " +
            "FROM app.votes " +
                "FULL OUTER JOIN (" +
                    "SELECT vote_id, array_agg(genre_id) AS genres " +
                    "FROM app.vote_genres " +
                    "GROUP BY vote_id) genres_arr " +
                    "ON id=vote_id " +
                "FULL OUTER JOIN app.vote_artist ON id = app.vote_artist.vote_id;";

    public VoteDaoDB(IDataSourceWrapper ds){
        this.ds = ds;
    }
    @Override
    public List<Vote> getVoteList() {
        List<Vote> votes = new LinkedList<>();

        try {
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getVoteSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
//                long id = resultSet.getLong("app.votes.id");
                long id = resultSet.getLong("id");
                String about = resultSet.getString("about");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String email = resultSet.getString("email");
                Array array = resultSet.getArray("genres");
                Long[] genresLong = (Long[]) array.getArray();
                long artist = resultSet.getLong("artist");
                //------------------------------------------------------------------------------
                votes.add(new Vote(id,new VoteDTO(artist,arrayLongs(genresLong),about, email,date)));
                //------------------------------------------------------------------------------
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return votes;
    }

    @Override
    public boolean save(VoteDTO vote) {
        try {
           Connection connection = ds.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(saveVoteSQL,
                   Statement.RETURN_GENERATED_KEYS
           );
           preparedStatement.setString(1,vote.getMessage());
           preparedStatement.setString(2, vote.getEmail());
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
        try {
            Connection connection = ds.getConnection();
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
        try {
            Connection connection = ds.getConnection();
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
