package by.it_academy.jd2.MJD29522.dao.dataBase;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.ds.api.IDataSourceWrapper;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingerDaoDB implements ISingerDao {

    private final IDataSourceWrapper dataSource;
    private final String GET_SINGERS_SQL = "SELECT id, name FROM app.artists;";
    private final String ADD_SINGERS_SQL = "INSERT INTO app.artists (name) VALUES (?);";
    private final String UPDATE_SINGERS_SQL = "UPDATE app.artists SET name = ? WHERE id = ?;";
    private final String DELETE_SINGERS_SQL = "DELETE FROM app.artists WHERE id = ?;";
    private final String EXIST_SINGERS_SQL = "SELECT id FROM app.artist WHERE id = ?";
    private final String GET_NAME_SINGERS_SQL = "SELECT name FROM app.artist WHERE id = ?";
    public SingerDaoDB(IDataSourceWrapper wrapper) {
        this.dataSource = wrapper;
    }

    @Override
    public List<SingerID> get() {
        List<SingerID> singers = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_SINGERS_SQL) ){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                singers.add(new SingerID(new SingerDTO(name), id));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return singers;
    }

    @Override
    public boolean add(String newSinger) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    ADD_SINGERS_SQL) ){
            preparedStatement.setString(1, newSinger);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void update(long id, String name) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_SINGERS_SQL)){
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_SINGERS_SQL)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            }
        return false;
    }

    @Override
    public boolean exist(long id) {
        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(EXIST_SINGERS_SQL);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if(id == resultSet.getLong("id"));{
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String getName(long id) {
        String name = null;
        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(GET_NAME_SINGERS_SQL);
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
