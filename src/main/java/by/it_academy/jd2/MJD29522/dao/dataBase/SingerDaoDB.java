package by.it_academy.jd2.MJD29522.dao.dataBase;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.api.IDataSourceWrapper;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import by.it_academy.jd2.MJD29522.util.StartingDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingerDaoDB implements ISingerDao {
    private final IDataSourceWrapper ds;

    public SingerDaoDB(IDataSourceWrapper ds) {
        this.ds = ds;
    }

    @Override
    public List<SingerID> get() {
        List<SingerID> singers = new ArrayList<>();

        try(Connection conn = ds.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT id, name FROM app.artists;") ){
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
        try(Connection conn = ds.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO app.artists (name) VALUES (?);") ){
            preparedStatement.setString(1, newSinger);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void update(long id, String name) {
        try(Connection conn = ds.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE app.artists SET name = ? WHERE id = ?;")){
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        try(Connection conn = ds.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM app.artists WHERE id = ?;")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            }
        return false;
    }

    @Override
    public boolean exist(long id) {
        boolean existID = false;
        try(Connection conn = ds.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM app.artists WHERE id = ?;");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if(resultSet.getLong("id")==id){
                    existID = true;
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        List<SingerID> singers = get();
//       for (SingerID singerID : singers){
//           if(id == singerID.getId()) {
//               return true;
//           }
//       }
       return existID;
    }

    @Override
    public String getName(long id) {
        String name = null;
        try {
            Connection conn = ds.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT name FROM app.artists WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
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
