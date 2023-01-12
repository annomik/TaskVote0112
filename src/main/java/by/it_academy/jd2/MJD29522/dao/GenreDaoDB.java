package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDaoDB;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.dto.GenreID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoDB implements IGenreDaoDB {

    private List<GenreID> genres = new ArrayList<>();

    @Override
    public List<GenreID> get() {
        try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        try(Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/voiting",
                "postgres",
                "postgres");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, name FROM app.genres;")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            genres.clear();

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
        try(Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/voiting",
                "postgres",
                "postgres");
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
        try(Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/voiting",
                "postgres",
                "postgres");
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
        for (GenreID genreID : genres) {
            if(id == genreID.getId()){
                return true;
            }
        }
        return false;
    }

    public boolean exist(String genre) {
        for (GenreID genreID : genres) {
            if (genre.equals(genreID.getGenreDTO().getName())) {
                return true;
            }
        }
        return false;
    }
}
