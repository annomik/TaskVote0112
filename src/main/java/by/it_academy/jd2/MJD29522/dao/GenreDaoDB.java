package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.dto.GenreID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoDB implements IGenreDao {

    StartingDB startingDB = new StartingDB();

    @Override
    public List<GenreID> get() {
        List<GenreID> genres = new ArrayList<>();

        try(Connection conn = startingDB.load();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, name FROM app.genres;")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                genres.add(new GenreID(new GenreDTO(name), id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genres;
    }

    @Override
    public boolean add(String newGenre) {
        try(Connection conn = startingDB.load();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO app.genres (name) VALUES (?);")) {
            preparedStatement.setString(1, newGenre);

            preparedStatement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return true;
}

    @Override
    public void update(long id, String name) {
        try(Connection conn = startingDB.load();
                PreparedStatement preparedStatement = conn.prepareStatement(
                        "UPDATE app.genres SET name = ? WHERE id = ?;")){
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean exist(long id) {
        List<GenreID> genres = get();
        for (GenreID genreID : genres) {
            if(id == genreID.getId()){
                return true;
            }
        }
        return false;
    }
}
