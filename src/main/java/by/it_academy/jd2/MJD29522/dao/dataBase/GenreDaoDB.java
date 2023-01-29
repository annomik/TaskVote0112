package by.it_academy.jd2.MJD29522.dao.dataBase;

import by.it_academy.jd2.MJD29522.dao.api.IGenreDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.ds.api.IDataSourceWrapper;
import by.it_academy.jd2.MJD29522.dto.GenreDTO;
import by.it_academy.jd2.MJD29522.dto.GenreID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoDB implements IGenreDao {

    private final IDataSourceWrapper dataSource;
    private final String GET_GENRES_SQL = "SELECT id, name FROM app.genres ORDER BY id;";
    private final String ADD_GENRES_SQL = "INSERT INTO app.genres (name) VALUES (?);";
    private final String UPDATE_GENRES_SQL = "UPDATE app.genres SET name = ? WHERE id = ?;";
    private final String DELETE_GENRES_SQL = "DELETE FROM app.genres WHERE id = ?;";
    private final String EXIST_GENRES_SQL = "SELECT id FROM app.genres WHERE id = ?";
    private final String GET_NAME_GENRES_SQL = "SELECT name FROM app.genres WHERE id = ?";
    public GenreDaoDB(IDataSourceWrapper wrapper){
        this.dataSource = wrapper;
    }

    @Override
    public synchronized List<GenreID> get() {
        List<GenreID> genres = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_GENRES_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                genres.add(new GenreID(new GenreDTO(name), id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genres;
    }

    @Override
    public synchronized boolean add(String newGenre) {
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    ADD_GENRES_SQL)) {
            preparedStatement.setString(1, newGenre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public synchronized void update(long id, String name) {
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_GENRES_SQL)){
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized boolean delete(long id) {
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_GENRES_SQL))
        {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public synchronized boolean exist(long id) {
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(EXIST_GENRES_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long idDB = resultSet.getLong("id");
                System.out.println(idDB);
                if (idDB == id) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName(long id) {
            String name = null;
            try(Connection conn = dataSource.getConnection()) {
                PreparedStatement preparedStatement = conn.prepareStatement(GET_NAME_GENRES_SQL);
                preparedStatement.setLong(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    name = resultSet.getString("name");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return name;
    }
}
