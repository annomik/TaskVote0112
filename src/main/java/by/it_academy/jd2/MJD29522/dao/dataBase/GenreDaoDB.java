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
    public GenreDaoDB(IDataSourceWrapper wrapper){
        this.dataSource = wrapper;
    }

    @Override
    public synchronized List<GenreID> get() {
        List<GenreID> genres = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, name FROM app.genres " +
                    "ORDER BY id;")) {
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
    public synchronized boolean add(String newGenre) {
        try(Connection conn = this.dataSource.getConnection();
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
    public synchronized void update(long id, String name) {
        try(Connection conn = this.dataSource.getConnection();
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
    public synchronized boolean delete(long id) {
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM app.genres WHERE id = ?;"))
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
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM app.genres WHERE id = ?;")) {
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
}
